package com.malikov.freelance.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

//@NamedQueries({
//        @NamedQuery(name = Client.DELETE, query = "DELETE FROM Client c WHERE c.id=:id"),
//        @NamedQuery(name = Client.BY_NAME, query = "SELECT c FROM Client c WHERE c.name=:name"),
//        @NamedQuery(name = Client.BY_LAST_NAME, query = "SELECT c FROM Client c WHERE c.lastName=:lastName"),
//        @NamedQuery(name = Client.BY_FIRST_NAME_MASK, query = "SELECT c FROM Client c WHERE lower(c.name) LIKE lower(:firstNameMask)"),
//        @NamedQuery(name = Client.BY_LAST_NAME_MASK, query = "SELECT c FROM Client c WHERE lower(c.lastName) LIKE lower(:lastNameMask)"),
//        @NamedQuery(name = Client.BY_EMAIL, query = "SELECT c FROM Client c WHERE c.email=:email"),
//        @NamedQuery(name = Client.ALL_SORTED, query = "SELECT c FROM Client c JOIN c.roles as rls WHERE rls = 'CLIENT' ORDER BY c.id"),
//})
@Entity
@Table(name = "user")
public class Client extends BaseUser {


    public static final String DELETE = "Client.delete";
//    public static final String BY_NAME = "Client.getByName";
//    public static final String BY_LAST_NAME = "Client.getByLastName";
//    public static final String BY_FIRST_NAME_MASK = "Client.getByFirstNameMask";
//    public static final String BY_LAST_NAME_MASK = "Client.getByLastNameMask";
//    public static final String BY_EMAIL = "Client.getByEmail";
    public static final String ALL_SORTED = "Client.getAllSorted";


    public Client() {
    }

    public Client(String login, String password, String firstName, String lastName, String email, Set<Role> roles) {
        super(login, password, firstName, lastName, email, roles);
    }

    public Client(Integer id, String login, String password, String firstName, String lastName, String email, Set<Role> roles) {
        super(id, login, password, firstName, lastName, email, roles);
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", roles=" + roles +
                '}';
    }
}
