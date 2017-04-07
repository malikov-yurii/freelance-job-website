package com.malikov.freelance.web;

import com.malikov.freelance.model.Skill;
import com.malikov.freelance.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public abstract class AbstractController {

    @Autowired
    SkillService skillService;

    public Set<Skill> persistNewSkills(Collection<Skill> newSkillCollection) {
        List<Skill> allSkills = skillService.getAll();
        Set<Skill> newProjectPersistedSkillList = new HashSet<>();
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
