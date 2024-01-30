package com.desafio.spring.error;

public class UnauthorizedException  extends RuntimeException {

    private static final long serialVersionUID = 720344521383255358L;

    public UnauthorizedException (String errorMessage) {
        super(errorMessage);
    }
}
