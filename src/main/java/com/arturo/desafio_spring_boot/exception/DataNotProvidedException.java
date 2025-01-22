package com.arturo.desafio_spring_boot.exception;

public class DataNotProvidedException extends Exception {
    
    public DataNotProvidedException(String message) {
        super(message);
    }

    public DataNotProvidedException(String message, Throwable cause) {
        super(message, cause);
    }

}
