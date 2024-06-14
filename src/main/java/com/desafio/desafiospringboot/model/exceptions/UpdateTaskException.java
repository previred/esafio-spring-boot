package com.desafio.desafiospringboot.model.exceptions;

public class UpdateTaskException extends RuntimeException{

    public UpdateTaskException(String mensaje){
        super(mensaje);
    }

}
