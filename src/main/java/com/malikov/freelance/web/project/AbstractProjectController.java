package com.malikov.freelance.web.project;

import com.malikov.freelance.model.*;
import com.malikov.freelance.service.*;
import com.malikov.freelance.to.ProjectTo;
import com.malikov.freelance.util.ProjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.malikov.freelance.model.ApplicationStatus.*;

public abstract class AbstractProjectController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractProjectController.class);

    @Autowired
    private MessageSource messageSource;
    //
//    @Autowired
//    private FreelancerService orderService;
//
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private CommentService commentService;

    //
//    @Autowired
//    private ClientService customerService;
//
//
//    @Autowired
//    private ProductVariationService productVariationService;
//
//    public OrderTo getOrderTo(int id) {
//        LOG.info("get order {}", id);
//        return OrderUtil.asTo(orderService.get(id));
//    }
//
//    public Order getOrder(int id) {
//        LOG.info("get order {}", id);
//        return orderService.get(id);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete order {}", id);
//        orderService.delete(id);
//    }
//
    public List<ProjectTo> getAll() {
        LOG.info("getAll orders");
        List<ProjectTo> projectTos = new ArrayList<>();

        ApplicationStatus applicationStatus;

        User authorizedUser = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (authorizedUser.getRoles().contains(Role.ROLE_ADMIN)) {
            projectTos.addAll(projectService.getAll().stream().map(project -> ProjectUtil.asTo(project, ApplicationStatus.NOT_FREELANCER, authorizedUser)).collect(Collectors.toList()));
        } else if (authorizedUser.getRoles().contains(Role.ROLE_CLIENT)) {
            projectTos.addAll(projectService.getAll().stream().filter(project -> !project.getBlocked()).map(project -> ProjectUtil.asTo(project, ApplicationStatus.NOT_FREELANCER, authorizedUser)).collect(Collectors.toList()));
        } else {
            Freelancer authorizedFreelancer = freelancerService.get(authorizedUser.getId());
            for (Project project : projectService.getAll()) {
                if (project.getBlocked())
                    continue;
                if (project.getStatus() != ProjectStatus.LOOKING_FOR_FREELANCER) {
                    applicationStatus = NOT_LOOKING_FOR_A_FREELANCER;
                } else if (project.getAppliedFreelancers().contains(authorizedFreelancer)) {
                    applicationStatus = ALREADY_APPLIED;
                } else if (authorizedFreelancer.getSkills().containsAll(project.getRequiredSkills())) {
                    applicationStatus = ALLOWED_HAS_SKILLS;
                } else {
                    applicationStatus = NOT_ALLOWED_LACK_OF_SKILLS;
                }
                projectTos.add(ProjectUtil.asTo(project, applicationStatus, authorizedUser));
            }
        }
        return projectTos;
    }

    public void applyForProject(int id) {
        Project project = projectService.get(id);
        project.getAppliedFreelancers().add(
                freelancerService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        projectService.save(project);
    }

    public void discardApplicationForProject(int id) {
        Project project = projectService.get(id);
        project.getAppliedFreelancers().remove(
                freelancerService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        projectService.save(project);
    }

    public ResponseEntity<String> create(ProjectTo projectTo) {
        BaseUser user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            projectService.save(ProjectUtil.newFromTo(projectTo
                    , clientService.get(projectTo.getClientId())
                    , getSkillsFromProjectTo(projectTo)));
        } else {
            projectService.save(ProjectUtil.newFromTo(projectTo
                    , clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())
                    , getSkillsFromProjectTo(projectTo)));
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<String> update(ProjectTo projectTo) {
        BaseUser user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Project project = projectService.get(projectTo.getId());

        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            project.setClient(clientService.get(projectTo.getClientId()));
        } else if (!Objects.equals(projectService.get(projectTo.getId()).getClient().getId(), user.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        project.setName(projectTo.getName());
        project.setDescription(projectTo.getDescription());
        project.setPayment(projectTo.getPayment());
        project.setRequiredSkills(getSkillsFromProjectTo(projectTo));
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   private List<Skill> getSkillsFromProjectTo(ProjectTo projectTo) {
        List<Skill> allSkills = skillService.getAll();
        Set<Skill> rawSkillSet = Arrays.stream(projectTo
                .getRequiredSkills()
                .split("\\W*,\\W*"))
                .map(Skill::new)
                .collect(Collectors.toSet());
        List<Skill> newProjectPersistedSkillList = new ArrayList<>();
        for (Skill skill : rawSkillSet) {
            int skillId = allSkills.indexOf(skill);
            if (skillId == -1) {
                newProjectPersistedSkillList.add(skillService.save(skill));
            } else {
                newProjectPersistedSkillList.add(allSkills.get(skillId));
            }
        }
        return newProjectPersistedSkillList;
    }

    public void approveFreelancer(int projectId, int freelancerId) {
        Project project = projectService.get(projectId);
        project.setFreelancer(freelancerService.get(freelancerId));
        project.setAppliedFreelancers(null);
        project.setStatus(ProjectStatus.IN_PROGRESS);
        projectService.save(project);
    }

    public void setIsCommentBlocked(int commentId, boolean isBlocked) {
        Comment comment = commentService.get(commentId);
        comment.setBlocked(isBlocked);
        commentService.save(comment);
    }

    public void updateIsProjectBlocked(int projectId, boolean isBlocked) {
        Project project = projectService.get(projectId);
        project.setBlocked(isBlocked);
        projectService.save(project);
    }

    public void addComment(int projectId, String commentText) {
        User user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        commentService.save(new Comment(projectId, LocalDateTime.now(), user.getFirstName() + " " + user.getLastName(), commentText));
    }

    public void delete(int projectId) {
        projectService.delete(projectId);
    }

    public ResponseEntity<String> updateProjectStatus(int projectId, ProjectStatus status) {
        BaseUser user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Project project = projectService.get(projectId);
        if (user.getRoles().contains(Role.ROLE_ADMIN) || (user.getRoles().contains(Role.ROLE_CLIENT) && user.getId() == project.getClient().getId())) {
            project.setStatus(status);
            projectService.save(project);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
    }
}