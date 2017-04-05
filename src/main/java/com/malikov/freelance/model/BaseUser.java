package com.malikov.freelance.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public class BaseUser extends BaseEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "blocked")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean blocked;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Role> roles;


    public BaseUser(){}

    public BaseUser(Integer id, String login, String password, String firstName, String lastName, String email, Role... roles) {
        this(id, login, password, firstName, lastName, email, new HashSet<>(Arrays.asList(roles)));
    }
    public BaseUser(String login, String password, String firstName, String lastName, String email, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.blocked = Boolean.FALSE;
    }

    public BaseUser(Integer id, String login, String password, String firstName, String lastName, String email, Set<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.blocked = Boolean.FALSE;
    }

    public BaseUser(BaseUser baseUser) {
        this(baseUser.getId(), baseUser.getLogin(), baseUser.getPassword(), baseUser.getFirstName(), baseUser.getLastName()
                , baseUser.getEmail(), baseUser.getRoles());
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseUser)) return false;
        if (!super.equals(o)) return false;
        BaseUser baseUser = (BaseUser) o;
        return Objects.equals(login, baseUser.login) &&
                Objects.equals(password, baseUser.password) &&
                Objects.equals(firstName, baseUser.firstName) &&
                Objects.equals(lastName, baseUser.lastName) &&
                Objects.equals(email, baseUser.email) &&
                Objects.equals(roles, baseUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, firstName, lastName, email, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
