package com.malikov.freelance.util;

import com.malikov.freelance.model.BaseUser;
import com.malikov.freelance.model.Role;
import com.malikov.freelance.to.BaseUserTo;

public class BaseUserUtil {

    public static BaseUser prepareToSave(BaseUser baseUser) {
        baseUser.setPassword(PasswordUtil.encode(baseUser.getPassword()));
        baseUser.setEmail(baseUser.getEmail().toLowerCase());
        return baseUser;
    }

    public static BaseUserTo asTo(BaseUser baseUser){
        return new BaseUserTo(
                baseUser.getId()
                ,baseUser.getFirstName() == null ? "" : baseUser.getFirstName()
                ,baseUser.getLastName() == null ? "" : baseUser.getLastName()
                ,baseUser.getLogin()
                ,null
                ,baseUser.getEmail()
                ,baseUser.getBlocked()
                ,baseUser.getRoles().contains(Role.ROLE_ADMIN) ? "admin" : (baseUser.getRoles().contains(Role.ROLE_CLIENT) ? "client" : "freelancer")
        );
    }

    public static BaseUser newFromTo(BaseUserTo baseUserTo) {
        return new BaseUser(null, baseUserTo.getLogin(), baseUserTo.getPassword()
                , baseUserTo.getFirstName(), baseUserTo.getLastName(), baseUserTo.getEmail());
    }

    public static void updateFromTo(BaseUser baseUser, BaseUserTo baseUserTo) {
        baseUser.setFirstName(baseUserTo.getFirstName());
        baseUser.setLastName(baseUserTo.getLastName());
        baseUser.setLogin(baseUserTo.getLogin());
        baseUser.setPassword(baseUserTo.getPassword());
        baseUser.setEmail(baseUserTo.getEmail());
    }
}
