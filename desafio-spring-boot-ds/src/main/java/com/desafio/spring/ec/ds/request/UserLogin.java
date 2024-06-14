package com.desafio.spring.ec.ds.request;

import java.util.Objects;

public record UserLogin(String username, String password) {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public UserLogin {
        Objects.nonNull(password);
        if (password.length() < 5) {
            throw new RuntimeException("Password no cumple con lo requisitos mínimos");
        }
    }

}