package com.malikov.freelance.service;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;

import static com.malikov.freelance.ClientTestData.*;

public class ClientServiceTest extends AbstractServiceTest{
    @Autowired
    protected ClientService service;

    @Test
    public void save() throws Exception {
        Client newClient = new Client("newClientLogin", "newClientPassword", "newClientFirstName", "newClientLastName",
                "newClientEmail", new HashSet<>(Arrays.asList(Role.USER, Role.CLIENT)));
        Client created = service.save(newClient);
        CLIENT_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        SIMA_CLIENT_1, ROZA_CLIENT_2, ISAAK_CLIENT_3, created),
                service.getAll());
    }

    @Test
    public void update() throws Exception {
        Client updated = new Client(ROZA_CLIENT_2);
        updated.setFirstName("Roza_Updated");
        service.update(updated);
        CLIENT_MATCHER.assertEquals(updated, service.get(ROZA_CLIENT_2.getId()));
    }

    @Test
    public void get() throws Exception {
        Client client = service.get(SIMA_CLIENT_1.getId());
        CLIENT_MATCHER.assertEquals(SIMA_CLIENT_1, client);
    }

    @Test
    public void getAll() throws Exception {
        CLIENT_MATCHER.assertCollectionEquals(Arrays.asList(
                SIMA_CLIENT_1, ROZA_CLIENT_2, ISAAK_CLIENT_3), service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(ROZA_CLIENT_2.getId());
        CLIENT_MATCHER.assertCollectionEquals(Arrays.asList(
                SIMA_CLIENT_1, ISAAK_CLIENT_3), service.getAll());

    }

}