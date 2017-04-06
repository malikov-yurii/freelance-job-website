package com.malikov.freelance.web.skill;

import com.malikov.freelance.model.Skill;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/skills")
public class SkillAjaxController extends AbstractSkillController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Skill> getAll() {
        return super.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void createOrUpdate(@Valid Skill skillTo){
        if (skillTo.isNew()) {
            super.create(skillTo);
        } else {
            super.update(skillTo);
        }
    }

}
