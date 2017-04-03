package com.malikov.freelance.model;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Skill.DELETE, query = "DELETE FROM Skill p WHERE p.id=:id"),
//        @NamedQuery(name = Skill.BY_CLIENT, query = "SELECT p FROM Skill p WHERE p.client.id=:clientId"),
//        @NamedQuery(name = Skill.BY_FREELANCER, query = "SELECT p FROM Skill p WHERE p.freelancer.id=:freelancerId"),
        @NamedQuery(name = Skill.ALL_SORTED, query = "SELECT p FROM Skill p ORDER BY p.id"),
})
@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {

    public static final String DELETE = "Skill.delete";
    public static final String ALL_SORTED = "Skill.getAllSorted";

    @Column(name = "name")
    private String name;

    public Skill(){}

    public Skill(String name){
        super(null);
        this.name = name;
    }

    public Skill(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name.toLowerCase(), skill.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + id + '\'' +
                " ,name='" + name + '\'' +
                '}';
    }
}
