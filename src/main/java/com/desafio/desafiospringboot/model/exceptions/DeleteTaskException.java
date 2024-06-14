package com.desafio.desafiospringboot.model.exceptions;

public class DeleteTaskException extends RuntimeException{

    public DeleteTaskException(String mensaje){
        super(mensaje);
    }

}
