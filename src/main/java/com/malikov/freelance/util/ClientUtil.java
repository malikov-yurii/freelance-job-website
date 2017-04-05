package com.malikov.freelance.util;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.to.ClientTo;

public class ClientUtil {

    public static ClientTo asTo(Client client){
        return new ClientTo(
                client.getId()
                ,client.getFirstName() == null ? "" : client.getFirstName()
                ,client.getLastName() == null ? "" : client.getLastName()
                ,client.getLogin()
                ,client.getPassword()
                ,client.getEmail()
                ,client.getBlocked()
        );
    }

    public static Client createNewFromTo(ClientTo clientTo) {
        return new Client(null, clientTo.getLogin(), clientTo.getPassword()
        , clientTo.getFirstName(), clientTo.getLastName(), clientTo.getEmail());
    }

    public static void updateFromTo(Client client, ClientTo clientTo) {
        client.setFirstName(clientTo.getFirstName());
        client.setLastName(clientTo.getLastName());
        client.setLogin(clientTo.getLogin());
        client.setPassword(clientTo.getPassword());
        client.setEmail(clientTo.getEmail());
    }

}
