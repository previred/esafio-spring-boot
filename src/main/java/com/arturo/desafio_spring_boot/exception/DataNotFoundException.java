package com.arturo.desafio_spring_boot.exception;

public class DataNotFoundException extends Exception {
    
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
