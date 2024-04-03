package com.previred.desafioApiRest.desafioprevired.exeptions;

public class RequestException extends RuntimeException {
    public RequestException(String message) {
        super(message);
    }
}