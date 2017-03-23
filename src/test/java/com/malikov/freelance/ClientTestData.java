package com.malikov.freelance;

import com.malikov.freelance.matcher.ModelMatcher;
import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Role;

import java.util.Objects;

public class ClientTestData {

    public static final Client SIMA_CLIENT_1 = new Client(7, "client1", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Sima", "Simov", "simov.sima@gmail.com", Role.USER, Role.CLIENT);
    public static final Client ROZA_CLIENT_2 = new Client(8, "client2", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Roza", "Rozova", "rozova.roza@gmail.com", Role.USER, Role.CLIENT);
    public static final Client ISAAK_CLIENT_3 = new Client(9, "client3", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Isaak", "Isaakov", "isaakov.isaak@gmail.com", Role.USER, Role.CLIENT);

    public static final ModelMatcher<Client> CLIENT_MATCHER = ModelMatcher.of(Client.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getLogin(), actual.getLogin())
                            && Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getFirstName(), actual.getFirstName())
                            && Objects.equals(expected.getLastName(), actual.getLastName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );
}
