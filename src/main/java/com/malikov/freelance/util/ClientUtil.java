package com.malikov.freelance.util;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Role;
import com.malikov.freelance.to.ClientUserTo;

import java.util.Arrays;
import java.util.HashSet;

public class ClientUtil {

    public static ClientUserTo asTo(Client client){
        return new ClientUserTo(BaseUserUtil.asTo(client));
    }

    public static Client newFromTo(ClientUserTo clientTo) {
        Client client = new Client(BaseUserUtil.newFromTo(clientTo));
        client.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_CLIENT)));
        return client;
    }

}
