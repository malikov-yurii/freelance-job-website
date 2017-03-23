package com.malikov.freelance.repository;

import com.malikov.freelance.model.User;

public interface UserRepository extends Repository<User> {

    User getByLogin(String login);
}
