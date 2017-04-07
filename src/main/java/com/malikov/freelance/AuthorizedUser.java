package com.malikov.freelance;

import com.malikov.freelance.model.BaseUser;
import com.malikov.freelance.to.BaseUserTo;
import com.malikov.freelance.util.BaseUserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private BaseUserTo baseUserTo;

    public AuthorizedUser(BaseUser baseUser) {
        super(baseUser.getLogin(), baseUser.getPassword(), true, true, true, true, baseUser.getRoles());
        this.baseUserTo = BaseUserUtil.asTo(baseUser);
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().baseUserTo.getId();
    }

    public void update(BaseUserTo newTo) {
        baseUserTo = newTo;
    }

    public BaseUserTo getBaseUserTo() {
        return baseUserTo;
    }

    @Override
    public String toString() {
        return baseUserTo.toString();
    }
}
