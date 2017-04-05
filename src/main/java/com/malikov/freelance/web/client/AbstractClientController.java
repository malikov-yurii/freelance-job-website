package com.malikov.freelance.web.client;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.service.ClientService;
import com.malikov.freelance.to.ClientTo;
import com.malikov.freelance.util.ClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractClientController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractClientController.class);

    @Autowired
    ClientService clientService;

    public List<ClientTo> getAll() {
        return clientService.getAll().stream().map(ClientUtil::asTo).collect(Collectors.toList());
    }

    public void updateIsClientBlocked(int clientId, boolean isBlocked) {
        Client client = clientService.get(clientId);
        client.setBlocked(isBlocked);
        clientService.save(client);
    }

    public void deleteClient(int clientId) {
        clientService.delete(clientId);
    }

    public void create(ClientTo clientTo) {
        clientService.save(ClientUtil.createNewFromTo(clientTo));
    }

    public void update(ClientTo clientTo) {
        Client client = clientService.get(clientTo.getId());
        ClientUtil.updateFromTo(client, clientTo);
        clientService.save(client);
    }

//
//    @Autowired
//    private ClientService service;
//
//    public com.malikov.freelance.model.Client get(int id) {
//        LOG.info("get order {}", id);
//        return service.get(id);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete order {}", id);
//        service.delete(id);
//    }
//
//    public List<CustomerTo> getAll() {
//        LOG.info("getAll customers");
//        return service.getAll().stream().map(CustomerUtil::asTo).collect(Collectors.toList());
//    }
//
//    public void update(com.malikov.freelance.model.Client customer, int id) {
//        customer.setId(id);
//        LOG.info("update order{}", customer);
//        service.update(customer);
//    }
//
//    public com.malikov.freelance.model.Client create(com.malikov.freelance.model.Client customer) {
//        customer.setId(null);
//        LOG.info("create order{}", customer);
//        return service.save(customer);
//    }
//
//    public String getEmail(int id) {
//        return service.get(id).getEmail();
//    }
//    public String getNote(int id) {
//        return service.get(id).getNote();
//    }
}