package com.malikov.freelance;

import com.malikov.freelance.matcher.ModelMatcher;
import com.malikov.freelance.model.Skill;

import java.util.Objects;

public class SkillTestData {

    public static final Skill HTML       = new Skill(1, "HTML");
    public static final Skill CSS        = new Skill(2, "CSS");
    public static final Skill JAVASCRIPT = new Skill(3, "Javascript");
    public static final Skill JAVA       = new Skill(4, "Java");
    public static final Skill SQL        = new Skill(5, "SQL");
    public static final Skill HIBERNATE  = new Skill(6, "Hibernate");
    public static final Skill SPRING     = new Skill(7, "Spring");
    public static final Skill MAVEN      = new Skill(8, "Maven");


    public static final ModelMatcher<Skill> SKILL_MATCHER = ModelMatcher.of(Skill.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                    )
    );
}
