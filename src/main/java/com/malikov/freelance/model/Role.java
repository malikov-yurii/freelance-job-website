package com.malikov.freelance.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_FREELANCER,
    ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
