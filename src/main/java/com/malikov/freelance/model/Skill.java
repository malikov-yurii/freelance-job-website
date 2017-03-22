package com.malikov.freelance.model;

import javax.persistence.*;

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
}
