package com.springboot.desafio.exceptions;

public class LoginException extends RuntimeException{

    public LoginException(String message) {
        super(message);
    }

}
