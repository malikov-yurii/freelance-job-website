package com.malikov.freelance.web;

import com.malikov.freelance.AuthorizedUser;
import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.model.Skill;
import com.malikov.freelance.model.User;
import com.malikov.freelance.service.ClientService;
import com.malikov.freelance.service.FreelancerService;
import com.malikov.freelance.service.SkillService;
import com.malikov.freelance.service.UserService;
import com.malikov.freelance.to.BaseUserTo;
import com.malikov.freelance.to.FreelancerUserTo;
import com.malikov.freelance.util.BaseUserUtil;
import com.malikov.freelance.util.ClientUtil;
import com.malikov.freelance.util.FreelancerUtil;
import com.malikov.freelance.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractController {

    @Autowired
    SkillService skillService;

    @Autowired
    UserService userService;

    @Autowired
    FreelancerService freelancerService;

    @Autowired
    ClientService clientService;

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

    public Object getAuthorizedUserTo(){
        switch (AuthorizedUser.safeGet().getBaseUserTo().getRole()){
            case "freelancer": return FreelancerUtil.asUserTo(freelancerService.get(AuthorizedUser.safeGet().getBaseUserTo().getId()));
            case "client": return ClientUtil.asTo(clientService.get(AuthorizedUser.safeGet().getBaseUserTo().getId()));
            case "admin": return BaseUserUtil.asTo(userService.get(AuthorizedUser.safeGet().getBaseUserTo().getId()));
        }
        throw new RuntimeException("Exception occurred while performing getAuthorizedUserTo()");
    };

    public void updateFreelancerProfile(FreelancerUserTo freelancerUserTo) {
        Freelancer freelancer = freelancerService.get(freelancerUserTo.getId());
        BaseUserUtil.updateFromTo(freelancer, freelancerUserTo);
        freelancer.setSkills(persistNewSkills(SkillUtil.getSkillSetFromString(freelancerUserTo.getSkills())));
        freelancerService.save(freelancer);
    }

    public void updateBaseUserProfile(BaseUserTo baseUserTo) {
        if ("client".equals(baseUserTo.getRole())) {
            Client client = clientService.get(baseUserTo.getId());
            BaseUserUtil.updateFromTo(client, baseUserTo);
            clientService.save(client);
        } else {
            User user = userService.get(baseUserTo.getId());
            BaseUserUtil.updateFromTo(user, baseUserTo);
            userService.save(user);
        }
    }
}
