package com.malikov.freelance.to;

public class FreelancerUserTo extends BaseUserTo {

    private String skills;

    public FreelancerUserTo(BaseUserTo baseUserTo, String skills){
        super(baseUserTo);
        this.skills = skills;
    }

    public FreelancerUserTo(){}

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
