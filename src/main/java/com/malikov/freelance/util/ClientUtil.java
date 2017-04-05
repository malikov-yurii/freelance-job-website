package com.malikov.freelance.util;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Role;
import com.malikov.freelance.to.ClientTo;

import java.util.Arrays;
import java.util.HashSet;

public class ClientUtil {

    public static ClientTo asTo(Client client){
        return new ClientTo(BaseUserUtil.asTo(client));
    }

    public static Client newFromTo(ClientTo clientTo) {
        Client client = new Client(BaseUserUtil.newFromTo(clientTo));
        client.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_CLIENT)));
        return client;
    }

    public static void updateFromTo(Client client, ClientTo clientTo) {
        BaseUserUtil.updateFromTo(client, clientTo);
    }
}
