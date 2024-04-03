package com.nuevospa.app.exceptions;

public class AuthTokenException extends RuntimeException {
    public AuthTokenException(String message) {
        super(message);
    }
}
