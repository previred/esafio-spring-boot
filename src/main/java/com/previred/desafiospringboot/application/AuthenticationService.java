package com.previred.desafiospringboot.application;

import com.previred.desafiospringboot.infrastructure.presentation.rest.exception.AuthenticationException;

public interface AuthenticationService {
    String authenticate(String name, String password) throws AuthenticationException;
}
