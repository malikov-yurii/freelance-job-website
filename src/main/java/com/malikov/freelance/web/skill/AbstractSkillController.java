package com.malikov.freelance.web.skill;

import com.malikov.freelance.model.Skill;
import com.malikov.freelance.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractSkillController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSkillController.class);

    @Autowired
    SkillService skillService;

    public List<Skill> getAll() {
        return skillService.getAll();
    }

    public void delete(int id) {
        skillService.delete(id);
    }

    public void create(Skill skill) {
        skill.setId(null);
        skillService.save(skill);
    }

    public void update(Skill updatedSkill) {
        Skill skill = skillService.get(updatedSkill.getId());
        skill.setName(updatedSkill.getName());
        skillService.save(skill);
    }
}
