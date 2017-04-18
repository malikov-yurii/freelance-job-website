package com.malikov.freelance.service;

import com.malikov.freelance.AuthorizedUser;
import com.malikov.freelance.model.User;
import com.malikov.freelance.repository.UserRepository;
import com.malikov.freelance.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = repository.getByLogin(login.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User with login=" + login + " has not found in DB");
        }
        if (u.getBlocked() != null && u.getBlocked()){
            throw new UsernameNotFoundException("User with login=" + login + " has been blocked by admin!");
        }
        return new AuthorizedUser(u);
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        return repository.save(user);
    }

//    @CacheEvict(value = "users", allEntries = true)
//    @Transactional
    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User getByLogin(String login) {
        return repository.getByLogin(login);
    }

    @Override
    public List<User> getAllAdmins() {
        return repository.getAllAdmins();
    }
}
