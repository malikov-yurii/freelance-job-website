package com.malikov.freelance.util;

import com.malikov.freelance.model.Skill;

import java.util.Collection;
import java.util.Iterator;

public class SkillTo {

    public static String asTo(Collection<Skill> skills){
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
}
