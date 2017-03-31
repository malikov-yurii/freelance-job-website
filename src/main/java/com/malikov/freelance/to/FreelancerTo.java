package com.malikov.freelance.to;

public class FreelancerTo {

    private Integer id;

    private String fullName;

    private String skills;

    public FreelancerTo(Integer id, String fullName, String skills) {
        this.id = id;
        this.fullName = fullName;
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
