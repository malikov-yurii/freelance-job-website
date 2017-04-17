package com.malikov.freelance.to;

public class ProjectSmallTo {

    private String name;

    private String description;

    private String clientLastName;

    private String requiredSkills;

    public ProjectSmallTo(
            String name
            , String description
            , String clientLastName
            , String requiredSkills
    ) {
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.clientLastName = clientLastName != null ? clientLastName : "";
        this.requiredSkills = requiredSkills != null ? requiredSkills: "";
    }

    public ProjectSmallTo(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}


