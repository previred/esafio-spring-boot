package com.desafio.desafiospringboot.model.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String mensaje){
        super(mensaje);
    }
}
