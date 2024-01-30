package com.desafio.spring.error;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 720344521383255358L;

    public ForbiddenException (String errorMessage) {
        super(errorMessage);
    }
}
