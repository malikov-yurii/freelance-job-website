package com.malikov.freelance.service;

import com.malikov.freelance.model.User;

import java.util.List;

public interface UserService extends Service<User> {

    User getByLogin(String login);

    List<User> getAllAdmins();

//    void update(UserTo userTo);
}
