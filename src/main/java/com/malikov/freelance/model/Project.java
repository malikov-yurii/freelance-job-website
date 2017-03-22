package com.malikov.freelance.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//@NamedQueries({
//        @NamedQuery(name = Project.DELETE, query = "DELETE FROM Project p WHERE p.id=:id"),
//        @NamedQuery(name = Project.BY_CLIENT, query = "SELECT p FROM Project p WHERE p.client.id=:clientId"),
//        @NamedQuery(name = Project.BY_FREELANCER, query = "SELECT p FROM Project p WHERE p.freelancer.id=:freelancerId"),
//        @NamedQuery(name = Project.ALL_SORTED, query = "SELECT p FROM Project p ORDER BY p.id"),
//})
//@Entity
//@Table(name = "projects")
public class Project extends BaseEntity {

    public static final String DELETE = "Project.delete";
    public static final String ALL_SORTED = "Project.getAllSorted";
    //    public static final String BY_CLIENT= "Project.getByClient";
//    public static final String BY_FREELANCER = "Project.getByFreelancer";


//    @Column(name = "name")
    private String name;

//    @Column(name = "status")
    private Status status;

//    @Column(name = "description")
    private String description;

//    @Column(name = "payment", columnDefinition = "decimal", precision = 12, scale = 2)
    private BigDecimal payment;

//    @OneToOne
//    @JoinColumn(name = "client_id")
    private Client client;

//    @OneToOne
//    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "project_applied_freelancers",
//            joinColumns = {@JoinColumn(name = "project_id")},
//            inverseJoinColumns = {@JoinColumn(name = "freelancer_id")
//            })
    private List<Freelancer> appliedFreelancers;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "project_required_skills",
//            joinColumns = @JoinColumn(name = "project_id"),
//            inverseJoinColumns = @JoinColumn(name = "skill_id")
//    )
    private Set<Skill> requiredSkills;

    public Project(String name, Status status, String description, BigDecimal payment, Client client, Freelancer freelancer, List<Freelancer> appliedFreelancers, Set<Skill> requiredSkills) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.payment = payment;
        this.client = client;
        this.freelancer = freelancer;
        this.appliedFreelancers = appliedFreelancers;
        this.requiredSkills = requiredSkills;
    }

    public Project(Integer id, String name, Status status, String description, BigDecimal payment, Client client, Freelancer freelancer, List<Freelancer> appliedFreelancers, Set<Skill> requiredSkills) {
        super(id);
        this.name = name;
        this.status = status;
        this.description = description;
        this.payment = payment;
        this.client = client;
        this.freelancer = freelancer;
        this.appliedFreelancers = appliedFreelancers;
        this.requiredSkills = requiredSkills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
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
                Objects.equals(requiredSkills, project.requiredSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, status, description, payment, client, freelancer, appliedFreelancers, requiredSkills);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", payment=" + payment +
                ", client=" + client +
                ", freelancer=" + freelancer +
                ", appliedFreelancers=" + appliedFreelancers +
                ", requiredSkills=" + requiredSkills +
                '}';
    }
}
