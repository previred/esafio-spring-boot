package com.springboot.desafio.exceptions;

public class AuthException extends RuntimeException{

    public AuthException(String message){
        super(message);
    }
}
