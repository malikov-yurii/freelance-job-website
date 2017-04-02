package com.malikov.freelance.web.project;

import com.malikov.freelance.model.*;
import com.malikov.freelance.service.*;
import com.malikov.freelance.to.ProjectTo;
import com.malikov.freelance.util.ProjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            projectTos.addAll(projectService.getAll().stream().map(project -> ProjectUtil.asTo(project, ApplicationStatus.NOT_FREELANCER, true)).collect(Collectors.toList()));
        } else if (authorizedUser.getRoles().contains(Role.ROLE_CLIENT)) {
            projectTos.addAll(projectService.getAll().stream().map(project -> ProjectUtil.asTo(project, ApplicationStatus.NOT_FREELANCER, false)).collect(Collectors.toList()));
        } else {
            Freelancer authorizedFreelancer = freelancerService.get(authorizedUser.getId());
            for (Project project : projectService.getAll()) {
                if (project.getStatus() != ProjectStatus.LOOKING_FOR_FREELANCER) {
                    applicationStatus = NOT_LOOKING_FOR_A_FREELANCER;
                } else if (project.getAppliedFreelancers().contains(authorizedFreelancer)) {
                    applicationStatus = ALREADY_APPLIED;
                } else if (authorizedFreelancer.getSkills().containsAll(project.getRequiredSkills())) {
                    applicationStatus = ALLOWED_HAS_SKILLS;
                } else {
                    applicationStatus = NOT_ALLOWED_LACK_OF_SKILLS;
                }
                projectTos.add(ProjectUtil.asTo(project, applicationStatus, false));
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

    public void create(ProjectTo projectTo) {

        List<Skill> allSkills = skillService.getAll();
        List<Skill> rawSkillList = new ArrayList<>(
                Arrays.stream(
                        projectTo
                                .getRequiredSkills()
                                .split("\\W*,\\W*"))
                        .map(Skill::new)
                        .collect(Collectors.toList()));
        List<Skill> newProjectPersistedSkillList = new ArrayList<>();
        for (Skill skill : rawSkillList) {
            int skillId = allSkills.indexOf(skill);
            if (skillId == -1) {
                newProjectPersistedSkillList.add(skillService.save(skill));
            } else {
                newProjectPersistedSkillList.add(allSkills.get(skillId));
            }
        }

        projectService.save(ProjectUtil.fromTo(projectTo
                , clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())
                , newProjectPersistedSkillList));
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

    public void addComment(int projectId, String commentText) {
        User user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        commentService.save(new Comment(projectId, LocalDateTime.now(), user.getFirstName() + " " + user.getLastName(), commentText));
    }
}