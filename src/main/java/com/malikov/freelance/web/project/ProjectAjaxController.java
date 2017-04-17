package com.malikov.freelance.web.project;

import com.malikov.freelance.model.ProjectStatus;
import com.malikov.freelance.to.ProjectSmallTo;
import com.malikov.freelance.to.ProjectStatusTo;
import com.malikov.freelance.to.ProjectTo;
import com.malikov.freelance.util.ProjectStatusUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/projects")
public class ProjectAjaxController extends AbstractProjectController {

    //    @Autowired
//    ClientService customerService;
//
//    @Autowired
//    UserService userService;
//

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid ProjectTo projectTo, BindingResult result, HttpEntity<String> httpEntity) {
        if (projectTo.isNew()) {
            return super.create(projectTo);
        } else {
            return super.update(projectTo);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectTo> getAll() {
        return super.getAll();
    }

/*//    @GetMapping(value = "/portfolio/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<ProjectSmallTo> getPortfolio(@PathVariable("id") int freelancerId) {
    public List<ProjectSmallTo> getPortfolio() {
        return super.getPortfolio(1);
    }*/

    //
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public OrderDatatablePageTo getDatatablePage(@RequestParam("start") int start, @RequestParam("length") int length) {
//        return super.getDatatablePage(start, length);
//    }
//
//    @GetMapping(value = "/{id}")
//    public OrderTo get(@PathVariable("id") int id) {
//        return super.getOrderTo(id);
//    }
    @PostMapping(value = "/{id}/apply-for-project")
    public void applyForProject(@PathVariable("id") int id) {
        super.applyForProject(id);
    }

    @PostMapping(value = "/{id}/discard-application-for-project")
    public void discardApplicationForProject(@PathVariable("id") int id) {
        super.discardApplicationForProject(id);
    }

    @PostMapping(value = "/{projectId}/approve-freelancer/{freelancerId}")
    public void approveFreelancer(@PathVariable("projectId") int projectId, @PathVariable("freelancerId") int freelancerId) {
        super.approveFreelancer(projectId, freelancerId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/block-comment/{id}")
    public void blockComment(@PathVariable("id") int commentId) {
        super.setIsCommentBlocked(commentId, true);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/unblock-comment/{id}")
    public void unblockComment(@PathVariable("id") int commentId) {
        super.setIsCommentBlocked(commentId, false);
    }

//    @PostMapping(value = "/{id}/add-comment")
//    public void addComment(@PathVariable("id") int projectId, @RequestParam("text") String commentText) {
//        super.addComment(projectId, commentText);
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/block")
    public void blockProject(@PathVariable("id") int projectId) {
        super.updateIsProjectBlocked(projectId, true);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/unblock")
    public void unblockProject(@PathVariable("id") int projectId) {
        super.updateIsProjectBlocked(projectId, false);
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int projectId) {
        super.delete(projectId);
    }

    @PostMapping(value = "/autocomplete-project-status")
    public List<ProjectStatusTo> autocompleteProjectStatus(){
        return new ArrayList<>(Arrays.asList(ProjectStatusUtil.asTo(ProjectStatus.LOOKING_FOR_FREELANCER)
                , ProjectStatusUtil.asTo(ProjectStatus.IN_PROGRESS)
                , ProjectStatusUtil.asTo(ProjectStatus.FINISHED)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping(value = "{id}/update-project-status")
    public ResponseEntity<String> updateProjectStatus(@PathVariable("id") int projectId, @RequestParam("projectStatus") ProjectStatus status){
        return super.updateProjectStatus(projectId, status);
    }

}
