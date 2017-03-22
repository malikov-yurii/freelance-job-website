package com.malikov.freelance.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_LOGIN, query = "SELECT u FROM User u WHERE u.login=:login"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.id"),
})
@Entity
@Table(name = "users")
public class User extends BaseUser {

    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.allSorted";
    public static final String BY_LOGIN = "User.getByLogin";

    public User(){}

    public User(Integer id, String login, String password, String firstName, String lastName, String email, Set<Role> roles) {
        super(id, login, password, firstName, lastName, email, roles);
    }

    public User(Integer id, String login, String password, String firstName, String lastName, String email, Role... roles) {
        super(id, login, password, firstName, lastName, email, new HashSet<Role>(Arrays.asList(roles)));
    }

    public User(String login, String password, String firstName, String lastName, String email, HashSet<Role> roles) {
        this(null, login, password, firstName, lastName, email, roles);
    }

    public User(User user){
        this(user.getId(), user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRoles());
    }
}
