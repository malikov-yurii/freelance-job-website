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

    //    @Override
//    public Collection<Client> getByName(String name) {
//        return repository.getByName(name);
//    }
//
//    @Override
//    public Collection<Client> getByLastName(String lastName) {
//        return repository.getByLastName(lastName);
//    }
//
//    @Override
//    public Collection<Client> getByFirstNameMask(String firstNameMask) {
//        return repository.getByFirstNameMask(firstNameMask);
//    }
//
//    @Override
//    public Collection<Client> getByLastNameMask(String lastNameMask) {
//        return repository.getByLastNameMask(lastNameMask);
//    }
//
//    @Override
//    public Collection<Client> getByPhoneNumberMask(String phoneNumberMask) {
//        return repository.getByPhoneNumberMask(phoneNumberMask);
//    }
//
//    @Override
//    public Collection<Client> getByCityMask(String cityMask) {
//        return repository.getByCityMask(cityMask);
//    }
//
//    @Override
//    public Collection<Client> getByCity(String city) {
//        return repository.getByCity(city);
//    }
//
//    @Override
//    public Client getByEmail(String email) {
//        return repository.getByEmail(email);
//    }
//
//    @Override
//    public Client getByPhoneNumber(String phoneNumber) {
//        return repository.getByPhoneNumber(phoneNumber);
//    }
}
