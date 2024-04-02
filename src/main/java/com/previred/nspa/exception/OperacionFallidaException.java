package com.previred.nspa.exception;


public class OperacionFallidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OperacionFallidaException(String message) {
        super(message);
    }

    public OperacionFallidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
