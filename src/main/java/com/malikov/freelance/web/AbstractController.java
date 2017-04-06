package com.malikov.freelance.web;

import com.malikov.freelance.model.Skill;
import com.malikov.freelance.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractController {

    @Autowired
    SkillService skillService;

    public List<Skill> persistNewSkills(Collection<Skill> newSkillCollection) {
        List<Skill> allSkills = skillService.getAll();
        List<Skill> newProjectPersistedSkillList = new ArrayList<>();
        for (Skill skill : newSkillCollection) {
            int skillId = allSkills.indexOf(skill);
            if (skillId == -1) {
                newProjectPersistedSkillList.add(skillService.save(skill));
            } else {
                newProjectPersistedSkillList.add(allSkills.get(skillId));
            }
        }
        return newProjectPersistedSkillList;
    }
}
