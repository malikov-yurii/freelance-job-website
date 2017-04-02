package com.malikov.freelance.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Project.DELETE, query = "DELETE FROM Project p WHERE p.id=:id"),
//        @NamedQuery(name = Project.BY_CLIENT, query = "SELECT p FROM Project p WHERE p.client.id=:clientId"),
//        @NamedQuery(name = Project.BY_FREELANCER, query = "SELECT p FROM Project p WHERE p.freelancer.id=:freelancerId"),
        @NamedQuery(name = Project.ALL_SORTED, query = "SELECT p FROM Project p ORDER BY p.id"),
})
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    public static final String DELETE = "Project.delete";
    public static final String ALL_SORTED = "Project.getAllSorted";
//        public static final String BY_CLIENT= "Project.getByClient";
//    public static final String BY_FREELANCER = "Project.getByFreelancer";


    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "description")
    private String description;

    @Column(name = "payment", columnDefinition = "decimal", precision = 12, scale = 2)
    private BigDecimal payment;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //We need this column in case client was deleted form db, but we still need his last name for project history
    @Column(name = "client_last_name")
    private String clientLastName;

    @OneToOne
    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

    @Column(name = "blocked")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean blocked;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "project_applied_freelancers",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "freelancer_id")
            })
    private List<Freelancer> appliedFreelancers;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "project_required_skills",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> requiredSkills;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "projectId")
    @Fetch(FetchMode.SELECT)
    @OrderBy("date_placed ASC")
    private List<Comment> comments;

    public Project(){}

    public Project(Integer id, String name, ProjectStatus status, String description, BigDecimal payment, Client client,
                   Freelancer freelancer, List<Freelancer> appliedFreelancers, List<Skill> requiredSkills, List<Comment> comments) {
        super(id);
        this.name = name;
        this.status = status;
        this.description = description;
        this.payment = payment;
        this.client = client;
        this.freelancer = freelancer;
        this.appliedFreelancers = appliedFreelancers;
        this.requiredSkills = requiredSkills;
        this.comments = comments;

        this.clientLastName = client.getLastName();

        this.blocked = Boolean.FALSE;
    }

    public Project(String name, ProjectStatus status, String description, BigDecimal payment, Client client,
                   Freelancer freelancer, List<Freelancer> appliedFreelancers, List<Skill> requiredSkills, List<Comment> comments) {
        this(null, name, status, description, payment, client, freelancer, appliedFreelancers, requiredSkills, comments);
    }

    public Project(Project project){
        this(project.getId(), project.getName(), project.getStatus(), project.getDescription(), project.getPayment(),
                project.getClient(), project.getFreelancer(), project.getAppliedFreelancers(), project.getRequiredSkills(), project.getComments());
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        this.clientLastName = client.getLastName();
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public List<Freelancer> getAppliedFreelancers() {
        return appliedFreelancers;
    }

    public void setAppliedFreelancers(List<Freelancer> appliedFreelancers) {
        this.appliedFreelancers = appliedFreelancers;
    }

    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
                status == project.status &&
                Objects.equals(description, project.description) &&
                Objects.equals(payment, project.payment) &&
                Objects.equals(client, project.client) &&
                Objects.equals(freelancer, project.freelancer) &&
                Objects.equals(appliedFreelancers, project.appliedFreelancers) &&
                Objects.equals(comments, project.comments) &&
                Objects.equals(requiredSkills, project.requiredSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, status, description, payment, client, freelancer, appliedFreelancers, requiredSkills);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", payment=" + payment +
                ", client=" + client +
                ", freelancer=" + freelancer +
                ", appliedFreelancers=" + getAppliedFreelancers() +
                ", requiredSkills=" + requiredSkills +
                ", comments=" + comments +
                '}';
    }
}
