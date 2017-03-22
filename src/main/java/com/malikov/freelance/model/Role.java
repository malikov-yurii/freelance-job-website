package com.malikov.freelance.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    FREELANCER,
    CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
