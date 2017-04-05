package com.malikov.freelance.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Client.GET, query = "SELECT c FROM Client c WHERE (c.id=:id AND 'ROLE_CLIENT' in elements(c.roles))"),
//        @NamedQuery(name = Client.GET, query = "SELECT c FROM Client c WHERE c.id=:id"),
//        @NamedQuery(name = Client.GET, query = "SELECT c FROM Client c LEFT JOIN c.roles as r WHERE r.name = 'CLIENT' AND c.id=:id"),
        @NamedQuery(name = Client.DELETE, query = "DELETE FROM Client c WHERE (c.id=:id AND 'ROLE_CLIENT' in elements(c.roles))"),
        @NamedQuery(name = Client.BY_LOGIN, query = "SELECT c FROM Client c WHERE c.login=:login AND ('ROLE_CLIENT' in elements(c.roles))"),
//        @NamedQuery(name = Client.BY_NAME, query = "SELECT c FROM Client c WHERE c.name=:name"),
//        @NamedQuery(name = Client.BY_LAST_NAME, query = "SELECT c FROM Client c WHERE c.lastName=:lastName"),
//        @NamedQuery(name = Client.BY_FIRST_NAME_MASK, query = "SELECT c FROM Client c WHERE lower(c.name) LIKE lower(:firstNameMask)"),
//        @NamedQuery(name = Client.BY_LAST_NAME_MASK, query = "SELECT c FROM Client c WHERE lower(c.lastName) LIKE lower(:lastNameMask)"),
//        @NamedQuery(name = Client.BY_EMAIL, query = "SELECT c FROM Client c WHERE c.email=:email"),
        @NamedQuery(name = Client.ALL_SORTED, query = "SELECT c FROM Client c WHERE ('ROLE_CLIENT' in elements(c.roles)) ORDER BY c.id"),
})
@Entity
@Table(name = "users")
public class Client extends BaseUser {


    public static final String GET = "Client.get";
    public static final String DELETE = "Client.delete";
//    public static final String BY_NAME = "Client.getByName";
//    public static final String BY_LAST_NAME = "Client.getByLastName";
//    public static final String BY_FIRST_NAME_MASK = "Client.getByFirstNameMask";
//    public static final String BY_LAST_NAME_MASK = "Client.getByLastNameMask";
//    public static final String BY_EMAIL = "Client.getByEmail";
    public static final String ALL_SORTED = "Client.getAllSorted";
    public static final String BY_LOGIN = "Client.getByLogin";


    public Client() {
    }

    public Client(String login, String password, String firstName, String lastName, String email) {
        this(null, login, password, firstName, lastName, email);
    }

    public Client(Integer id, String login, String password, String firstName, String lastName, String email) {
        super(id, login, password, firstName, lastName, email, Role.ROLE_USER, Role.ROLE_CLIENT);
    }

    public Client(Client client){
        this(client.getId(), client.getLogin(), client.getPassword(), client.getFirstName(), client.getLastName(), client.getEmail());
    }

    public Client(BaseUser baseUser) {
        super(baseUser);
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
