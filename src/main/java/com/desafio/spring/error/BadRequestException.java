package com.desafio.spring.error;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 720344521383255358L;

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
