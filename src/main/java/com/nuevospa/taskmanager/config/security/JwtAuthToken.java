package com.nuevospa.taskmanager.config.security;

import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class JwtAuthToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final transient Object credentials;
    private final transient Object principal;

    public JwtAuthToken(Object principal) {
        super(List.of());
        this.principal = principal;
        this.credentials = null;
        setAuthenticated(false);
    }

    public static JwtAuthToken fromContext(Object principal) {
        return new JwtAuthToken(principal);
    }


    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

}
