package com.malikov.freelance.model;

import java.util.Objects;
import java.util.Set;

//@NamedQueries({
//        @NamedQuery(name = Freelancer.DELETE, query = "DELETE FROM Freelancer c WHERE c.id=:id"),
//        @NamedQuery(name = Freelancer.BY_NAME, query = "SELECT c FROM Freelancer c WHERE c.name=:name"),
//        @NamedQuery(name = Freelancer.BY_LAST_NAME, query = "SELECT c FROM Freelancer c WHERE c.lastName=:lastName"),
//        @NamedQuery(name = Freelancer.BY_FIRST_NAME_MASK, query = "SELECT c FROM Freelancer c WHERE lower(c.name) LIKE lower(:firstNameMask)"),
//        @NamedQuery(name = Freelancer.BY_LAST_NAME_MASK, query = "SELECT c FROM Freelancer c WHERE lower(c.lastName) LIKE lower(:lastNameMask)"),
//        @NamedQuery(name = Freelancer.BY_EMAIL, query = "SELECT c FROM Freelancer c WHERE c.email=:email"),
//        @NamedQuery(name = Freelancer.ALL_SORTED, query = "SELECT f FROM Freelancer f JOIN f.roles as rls WHERE rls = 'FREELANCER' ORDER BY f.id"),
//})
//@Entity
public class Freelancer extends BaseUser {

    public static final String DELETE = "Freelancer.delete";
//    public static final String BY_NAME = "Freelancer.getByName";
//    public static final String BY_LAST_NAME = "Freelancer.getByLastName";
//    public static final String BY_FIRST_NAME_MASK = "Freelancer.getByFirstNameMask";
//    public static final String BY_LAST_NAME_MASK = "Freelancer.getByLastNameMask";
//    public static final String BY_EMAIL = "Freelancer.getByEmail";
    public static final String ALL_SORTED = "Freelancer.getAllSorted";

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "freelancer_skills",
//            joinColumns = {@JoinColumn(name = "freelancer_id")},
//            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
//    )
    private Set<Skill> skills;

    public Freelancer(){}

    public Freelancer(Set<Skill> skills) {
        this.skills = skills;
    }

    public Freelancer(String login, String password, String firstName, String lastName, String email, Set<Role> roles, Set<Skill> skills) {
        super(login, password, firstName, lastName, email, roles);
        this.skills = skills;
    }

    public Freelancer(Integer id, String login, String password, String firstName, String lastName, String email, Set<Role> roles, Set<Skill> skills) {
        super(id, login, password, firstName, lastName, email, roles);
        this.skills = skills;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Freelancer)) return false;
        if (!super.equals(o)) return false;
        Freelancer that = (Freelancer) o;
        return Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skills);
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", roles=" + getRoles() +
                "skills=" + skills +
                '}';
    }
}
