package com.nuevospa.taskmanager.exceptions;

import lombok.Getter;
import com.nuevospa.taskmanager.exceptions.enums.ExceptionEnum;

@Getter
public class NotFoundException extends RuntimeException {
    private final ExceptionEnum exception;

    public NotFoundException(Exception originalException, ExceptionEnum exception) {
        super(originalException);
        this.exception = exception;
    }

    public NotFoundException(String message, ExceptionEnum exception) {
        super(message);
        this.exception = exception;
    }

    public NotFoundException(ExceptionEnum exception) {
        this.exception = exception;
    }

    public NotFoundException(Throwable cause, ExceptionEnum exception) {
        super(cause);
        this.exception = exception;
    }
}
