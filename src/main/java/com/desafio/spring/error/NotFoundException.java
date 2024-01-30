package com.desafio.spring.error;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 720344521383255358L;

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
