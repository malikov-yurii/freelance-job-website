package com.malikov.freelance.to;

import com.malikov.freelance.model.BaseEntity;

public class BaseUserTo extends BaseEntity {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String email;

    private Boolean blocked;

    BaseUserTo(){}

    public BaseUserTo(Integer id, String firstName, String lastName, String login, String password, String email, Boolean blocked) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.blocked = blocked;
    }

    public BaseUserTo(BaseUserTo baseUserTo) {
        super(baseUserTo.getId());
        this.firstName = baseUserTo.getFirstName();
        this.lastName = baseUserTo.getLastName();
        this.login = baseUserTo.getLogin();
        this.password = baseUserTo.getPassword();
        this.email = baseUserTo.getEmail();
        this.blocked = baseUserTo.getBlocked();
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}