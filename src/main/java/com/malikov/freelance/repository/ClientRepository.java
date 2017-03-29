package com.malikov.freelance.repository;

import com.malikov.freelance.model.Client;

public interface ClientRepository extends Repository<Client> {

    Client getByLogin(String login);
}
