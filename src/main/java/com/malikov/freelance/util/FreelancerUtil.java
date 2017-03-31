package com.malikov.freelance.util;

import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.to.FreelancerTo;

public class FreelancerUtil {

    public static FreelancerTo asTo (Freelancer freelancer){
        return new FreelancerTo(freelancer.getId(), freelancer.getFirstName() + " " + freelancer.getLastName(), SkillTo.asTo(freelancer.getSkills()));
    }
}
