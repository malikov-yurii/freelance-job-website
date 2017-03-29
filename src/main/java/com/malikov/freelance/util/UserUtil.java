package com.malikov.freelance.util;

import com.malikov.freelance.model.BaseUser;
import com.malikov.freelance.to.UserTo;

public class UserUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

//    public static User createNewFromTo(UserTo newUser) {
//        return new User(null, newUser.getName(), newUser.getPassword(), Role.ROLE_USER);
//    }

    // TODO: 3/22/2017 Implement this
    public static UserTo asTo(BaseUser baseUser) {
        return
//                new UserTo(user.getId(), user.getName(), user.getPassword())
                null
                ;
    }
//
//    public static User updateFromTo(User user, UserTo userTo) {
//        user.setName(userTo.getName());
//        user.setPassword(userTo.getPassword());
//        return user;
//    }
//
//    public static User prepareToSave(User user) {
//        user.setPassword(PasswordUtil.encode(user.getPassword()));
//        return user;
//    }
}
