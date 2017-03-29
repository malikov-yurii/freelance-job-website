package com.malikov.freelance;

import com.malikov.freelance.matcher.ModelMatcher;
import com.malikov.freelance.model.BaseUser;
import com.malikov.freelance.model.Role;
import com.malikov.freelance.model.User;

import java.util.Objects;

public class UserTestData {
    public static final User YURII_FREELANCER_1 = new User(1, "freelancer1", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Yurii", "Malikov", "malikov.yurii@gmail.com", Role.ROLE_USER, Role.ROLE_FREELANCER);
    public static final User DENIS_FREELANCER_2 = new User(2, "freelancer2", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Denis", "Malikov", "malikov.denis@gmail.com", Role.ROLE_USER, Role.ROLE_FREELANCER);
    public static final User EGOR_FREELANCER_3 = new User(3, "freelancer3", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Egor", "Egorov", "egorov.egor@gmail.com", Role.ROLE_USER, Role.ROLE_FREELANCER);
    public static final User IVAN_FREELANCER_4 = new User(4, "freelancer4", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Ivan", "Ivanov", "ivanov.ivan@gmail.com", Role.ROLE_USER, Role.ROLE_FREELANCER);

    public static final User VAHTANG_ADMIN_1 = new User(5, "admin1", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Vahtang", "Vahtangov", "vahtangov.vahtang@gmail.com", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User GOGA_ADMIN_2 = new User(6, "admin2", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Goga", "Gogov", "gogov.goga@gmail.com", Role.ROLE_USER, Role.ROLE_ADMIN);




    public static final ModelMatcher<BaseUser> USER_MATCHER = ModelMatcher.of(BaseUser.class,
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
