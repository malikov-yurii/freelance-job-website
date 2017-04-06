package com.malikov.freelance.web.freelancer;

import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.service.FreelancerService;
import com.malikov.freelance.to.FreelancerUserTo;
import com.malikov.freelance.util.BaseUserUtil;
import com.malikov.freelance.util.FreelancerUtil;
import com.malikov.freelance.util.SkillUtil;
import com.malikov.freelance.web.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFreelancerController extends AbstractController{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractFreelancerController.class);

    @Autowired
    FreelancerService freelancerService;

    public List<FreelancerUserTo> getAll() {
        return freelancerService.getAll().stream().map(FreelancerUtil::asUserTo).collect(Collectors.toList());
    }

    public void blockUnblock(int freelancerId, boolean isBlocked) {
        Freelancer freelancer = freelancerService.get(freelancerId);
        freelancer.setBlocked(isBlocked);
        freelancerService.save(freelancer);
    }

    public void delete(int freelancerId) {
        freelancerService.delete(freelancerId);
    }

    public void create(FreelancerUserTo freelancerTo) {
        freelancerService.save(FreelancerUtil.newFromTo(freelancerTo, persistNewSkills(SkillUtil.getSkillSetFromString(freelancerTo.getSkills()))));

    }

    public void update(FreelancerUserTo freelancerTo) {
        Freelancer freelancer = freelancerService.get(freelancerTo.getId());
        BaseUserUtil.updateFromTo(freelancer, freelancerTo);
        freelancerService.save(freelancer);
    }

}