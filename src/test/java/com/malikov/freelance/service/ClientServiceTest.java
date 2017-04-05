package com.malikov.freelance.service;

import com.malikov.freelance.model.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.malikov.freelance.ClientTestData.*;

public class ClientServiceTest extends AbstractServiceTest{
    @Autowired
    protected ClientService service;

    @Test
    public void save() throws Exception {
        Client newClient = new Client("newClientLogin", "newClientPassword", "newClientFirstName", "newClientLastName",
                "newClientEmail");
        Client created = service.save(newClient);
        CLIENT_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        CLIENT_1_SIMA, CLIENT_2_ROZA, CLIENT_3_ISAAK, created),
                service.getAll());
    }

    @Test
    public void update() throws Exception {
        Client updated = new Client(CLIENT_2_ROZA);
        updated.setFirstName("Roza_Updated");
        service.update(updated);
        CLIENT_MATCHER.assertEquals(updated, service.get(CLIENT_2_ROZA.getId()));
    }

    @Test
    public void get() throws Exception {
        Client client = service.get(CLIENT_1_SIMA.getId());
        CLIENT_MATCHER.assertEquals(CLIENT_1_SIMA, client);
    }

    @Test
    public void getAll() throws Exception {
        CLIENT_MATCHER.assertCollectionEquals(Arrays.asList(
                CLIENT_1_SIMA, CLIENT_2_ROZA, CLIENT_3_ISAAK), service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(CLIENT_2_ROZA.getId());
        CLIENT_MATCHER.assertCollectionEquals(Arrays.asList(
                CLIENT_1_SIMA, CLIENT_3_ISAAK), service.getAll());

    }

}