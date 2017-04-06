package com.malikov.freelance.util;

import com.malikov.freelance.model.Skill;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillUtil {

    public static String skillCollectionToString(Collection<Skill> skills){
        String result = "";
        if (skills != null && skills.size() != 0) {
            Iterator<Skill> iterator = skills.iterator();
            result = iterator.next().getName();
            while (iterator.hasNext()){
                result += ", ";
                result += iterator.next().getName();
            }
        }
        return result;
    }

    public static Set<Skill> getSkillSetFromString(String skillsString) {
        return Arrays.stream(
                skillsString
                        .split("\\W*,\\W*"))
                .map(Skill::new)
                .collect(Collectors.toSet());
    }



}
