package com.malikov.freelance.util;

import com.malikov.freelance.model.Skill;

import java.util.*;
import java.util.stream.Collectors;

public class SkillUtil {

    public static String skillCollectionToString(Collection<Skill> skills){
        StringBuilder result = new StringBuilder();
        if (skills != null && skills.size() != 0) {
            Iterator<Skill> iterator = skills.iterator();
            result = new StringBuilder(iterator.next().getName());
            while (iterator.hasNext()){
                result.append(", ");
                result.append(iterator.next().getName());
            }
        }
        return result.toString();
    }

    public static Set<Skill> getSkillSetFromString(String skillsString) {
        if (skillsString == null || "".equals(skillsString))
            return Collections.emptySet();
        return Arrays.stream(
                skillsString
                        .split("\\W*,\\W*"))
                .map(Skill::new)
                .collect(Collectors.toSet());
    }



}
