package com.malikov.freelance.util;

import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.model.Role;
import com.malikov.freelance.model.Skill;
import com.malikov.freelance.to.FreelancerSmallTo;
import com.malikov.freelance.to.FreelancerUserTo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FreelancerUtil {

    public static FreelancerSmallTo asSmallTo(Freelancer freelancer){
        return new FreelancerSmallTo(freelancer.getId(), freelancer.getFirstName() + " " + freelancer.getLastName(), SkillUtil.skillCollectionToString(freelancer.getSkills()));
    }

    public static FreelancerUserTo asUserTo(Freelancer freelancer){
        return new FreelancerUserTo(BaseUserUtil.asTo(freelancer), SkillUtil.skillCollectionToString(freelancer.getSkills()));
    }

    public static Freelancer newFromTo(FreelancerUserTo freelancerUserTo, Set<Skill> persistedSkillList) {
        Freelancer freelancer = new Freelancer(BaseUserUtil.newFromTo(freelancerUserTo));
        freelancer.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_FREELANCER)));
        freelancer.setSkills(persistedSkillList);
        return freelancer;
    }

}
