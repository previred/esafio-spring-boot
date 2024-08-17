package com.nuevo.spa.desafio_spring_boot.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserDetails principal;

    public JwtAuthenticationToken(UserDetails principal, Object credentials) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserDetails getPrincipal() {
        return this.principal;
    }
}

