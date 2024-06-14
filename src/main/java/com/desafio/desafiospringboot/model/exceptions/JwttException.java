package com.desafio.desafiospringboot.model.exceptions;

public class JwttException extends RuntimeException{

    public JwttException(String message) {
        super(message);
    }
}
