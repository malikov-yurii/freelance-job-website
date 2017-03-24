package com.malikov.freelance;

import com.malikov.freelance.matcher.ModelMatcher;
import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.model.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import static com.malikov.freelance.SkillTestData.*;

public class FreelancerTestData {
    public static final Freelancer FREELANCER_1_YURII = new Freelancer(1, "freelancer1", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Yurii", "Malikov", "malikov.yurii@gmail.com", new HashSet<>(Arrays.asList(Role.USER, Role.FREELANCER)),
            new HashSet<>(Arrays.asList(HTML, CSS, JAVASCRIPT, JAVA, SQL, HIBERNATE, SPRING, MAVEN)));
    public static final Freelancer FREELANCER_2_DENIS = new Freelancer(2, "freelancer2", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Denis", "Malikov", "malikov.denis@gmail.com", new HashSet<>(Arrays.asList(Role.USER, Role.FREELANCER)), new HashSet<>(Arrays.asList(HTML, CSS, JAVASCRIPT)));
    public static final Freelancer FREELANCER_3_EGOR = new Freelancer(3, "freelancer3", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Egor", "Egorov", "egorov.egor@gmail.com", new HashSet<>(Arrays.asList(Role.USER, Role.FREELANCER)), new HashSet<>(Arrays.asList(JAVA, SQL, HIBERNATE, SPRING)));
    public static final Freelancer FREELANCER_4_IVAN = new Freelancer(4, "freelancer4", "$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm",
            "Ivan", "Ivanov", "ivanov.ivan@gmail.com", new HashSet<>(Arrays.asList(Role.USER, Role.FREELANCER)), new HashSet<>(Arrays.asList(HTML, CSS, JAVASCRIPT, JAVA, SQL)));

    public static final ModelMatcher<Freelancer> FREELANCER_MATCHER = ModelMatcher.of(Freelancer.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getLogin(), actual.getLogin())
                            && Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getFirstName(), actual.getFirstName())
                            && Objects.equals(expected.getLastName(), actual.getLastName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                            && Objects.equals(expected.getSkills(), actual.getSkills())
                    )
    );
}
