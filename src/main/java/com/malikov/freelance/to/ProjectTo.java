package com.malikov.freelance.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malikov.freelance.model.ApplicationStatus;
import com.malikov.freelance.model.Comment;
import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.model.ProjectStatus;
import com.malikov.freelance.util.CommentUtil;
import com.malikov.freelance.util.FreelancerUtil;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectTo {

    private Integer id;

    private Integer clientId;

    private String name;

    private String description;

    private BigDecimal payment;

    private String clientLastName;

    private ProjectStatus status;

    private List<FreelancerTo> appliedFreelancerTos;

    private List<CommentTo> commentTos;

    private String requiredSkills;

    private ApplicationStatus applicationStatus;

    public ProjectTo(
            Integer id
            , Integer clientId
            , String name
            , String description
            , BigDecimal payment
            , String clientLastName
            , ProjectStatus status
            , String requiredSkills
            , ApplicationStatus applicationStatus
            , List<Freelancer> freelancers
            , List<Comment> comments
    ) {
        this.id = id;
        this.clientId = clientId == null ? 0 : clientId;
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.payment = payment != null ? payment : new BigDecimal(0);
        this.clientLastName = clientLastName != null ? clientLastName : "";
        this.status = status;
        this.requiredSkills = requiredSkills != null ? requiredSkills: "";
        this.applicationStatus = applicationStatus;
        if (freelancers != null && freelancers.size() != 0) {
            appliedFreelancerTos = freelancers.stream().map(FreelancerUtil::asTo).collect(Collectors.toList());
        } else {
            appliedFreelancerTos = Collections.emptyList();
        }
        if (comments != null && comments.size() != 0) {
            commentTos = comments.stream().map(CommentUtil::asTo).collect(Collectors.toList());
        } else {
            commentTos = Collections.emptyList();
        }
    }

    public ProjectTo(){}

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

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

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<FreelancerTo> getAppliedFreelancerTos() {
        return appliedFreelancerTos;
    }

    public void setAppliedFreelancerTos(List<FreelancerTo> appliedFreelancerTos) {
        this.appliedFreelancerTos = appliedFreelancerTos;
    }

    public List<CommentTo> getCommentTos() {
        return commentTos;
    }

    public void setCommentTos(List<CommentTo> commentTos) {
        this.commentTos = commentTos;
    }

    @Override
    public String toString() {
        return "ProjectTo{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", payment='" + payment + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", status=" + status +
                ", requiredSkills='" + requiredSkills + '\'' +
                ", commentTos='" + commentTos + '\'' +
                '}';
    }
}
