package com.malikov.freelance.util;

import com.malikov.freelance.model.Role;
import com.malikov.freelance.model.User;
import com.malikov.freelance.to.BaseUserTo;

import java.util.Arrays;
import java.util.HashSet;

public class UserUtil {
    public static BaseUserTo asTo(User user){
        return BaseUserUtil.asTo(user);
    }

    public static User newFromTo(BaseUserTo baseUserTo) {
        User user = new User(BaseUserUtil.newFromTo(baseUserTo));
        user.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_CLIENT)));
        return user;
    }

    public static void updateFromTo(User user, BaseUserTo baseUserTo) {
        BaseUserUtil.updateFromTo(user, baseUserTo);
    }
}
