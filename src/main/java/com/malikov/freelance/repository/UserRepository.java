package com.malikov.freelance.repository;

import com.malikov.freelance.model.User;

import java.util.List;

public interface UserRepository extends Repository<User> {

    User getByLogin(String login);

    List<User> getAllAdmins();
}
