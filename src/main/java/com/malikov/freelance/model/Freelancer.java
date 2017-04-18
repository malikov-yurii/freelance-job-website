package com.malikov.freelance.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Freelancer.GET, query = "SELECT f FROM Freelancer f WHERE (f.id=:id AND 'ROLE_FREELANCER' in elements(f.roles))"),
        @NamedQuery(name = Freelancer.DELETE, query = "DELETE FROM Freelancer f WHERE (f.id=:id AND 'ROLE_FREELANCER' in elements(f.roles))"),
        @NamedQuery(name = Freelancer.BY_LOGIN, query = "SELECT c FROM Freelancer c WHERE c.login=:login AND ('ROLE_FREELANCER' in elements(c.roles))"),
        @NamedQuery(name = Freelancer.ALL_SORTED, query = "SELECT f FROM Freelancer f WHERE ('ROLE_FREELANCER' in elements(f.roles)) ORDER BY f.id"),
})
@Entity
@Table(name = "users")
public class Freelancer extends BaseUser {

    public static final String GET = "Freelancer.get";
    public static final String DELETE = "Freelancer.delete";
    public static final String ALL_SORTED = "Freelancer.getAllSorted";
    public static final String BY_LOGIN = "Freelancer.getByLogin";


    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "freelancer_skills",
            joinColumns = {@JoinColumn(name = "freelancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;

    public Freelancer(){}

    public Freelancer(Freelancer freelancer){
        super(freelancer.getId(), freelancer.getLogin(), freelancer.getPassword(), freelancer.getFirstName(),
                freelancer.getLastName(), freelancer.getEmail(), freelancer.getRoles());
        this.skills = freelancer.getSkills();
    }

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

    public Freelancer(BaseUser baseUser) {
        super(baseUser);
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
