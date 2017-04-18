package com.malikov.freelance.to;

import com.malikov.freelance.model.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Size;

public class BaseUserTo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @SafeHtml
    private String firstName;

    @NotEmpty
    @SafeHtml
    private String lastName;

    @NotEmpty
    @SafeHtml
    private String login;

    @NotEmpty
    @SafeHtml
    @Size(min = 4, max = 64, message = " must between 4 and 64 characters")
    private String password;

    @Email
    @NotEmpty
    @SafeHtml
    private String email;

    private Boolean blocked;

    private String role;

    public BaseUserTo(){}

    public BaseUserTo(Integer id, String firstName, String lastName, String login, String password, String email, Boolean blocked, String role) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.blocked = blocked;
        this.role = role;
    }

    public BaseUserTo(BaseUserTo baseUserTo) {
        super(baseUserTo.getId());
        this.firstName = baseUserTo.getFirstName();
        this.lastName = baseUserTo.getLastName();
        this.login = baseUserTo.getLogin();
        this.password = baseUserTo.getPassword();
        this.email = baseUserTo.getEmail();
        this.blocked = baseUserTo.getBlocked();
        this.role = baseUserTo.getRole();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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