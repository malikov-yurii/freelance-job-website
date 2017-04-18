package com.malikov.freelance.service;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.repository.ClientRepository;
import com.malikov.freelance.util.BaseUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public Client save(Client customer) {
        return repository.save((Client) BaseUserUtil.prepareToSave(customer));
    }

    @Override
    public Client update(Client customer) {
        return repository.save((Client) BaseUserUtil.prepareToSave(customer));
    }

    @Override
    public Client get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Client> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Client getByLogin(String login) {
        return repository.getByLogin(login);
    }

}
