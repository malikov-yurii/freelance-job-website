package com.malikov.freelance.web.freelancer;

import com.malikov.freelance.to.FreelancerUserTo;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/freelancers")
public class FreelancerAjaxController extends AbstractFreelancerController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FreelancerUserTo> getAll() {
        return super.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/block")
    public void blockFreelancer(@PathVariable("id") int id) {
        super.blockUnblock(id, true);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/unblock")
    public void unblockFreelancer(@PathVariable("id") int id) {
        super.blockUnblock(id, false);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void createOrUpdate(@Valid FreelancerUserTo freelancerUserTo){
        if (freelancerUserTo.isNew()) {
            super.create(freelancerUserTo);
        } else {
            super.update(freelancerUserTo);
        }
    }

}
