package com.malikov.freelance.service;

import com.malikov.freelance.model.User;

public interface UserService extends Service<User> {

    User getByLogin(String login);

//    void update(UserTo userTo);
}
