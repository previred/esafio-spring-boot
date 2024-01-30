package com.desafio.spring.error;

public class ConflictException extends RuntimeException {

    private static final long serialVersionUID = 720344521383255358L;

    public ConflictException(String errorMessage) {
        super(errorMessage);
    }
}
