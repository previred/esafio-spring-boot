package com.enterprise.move.apps.MoveApps.exception;

import lombok.Getter;

@Getter
public class TaskValidationException extends RuntimeException {

    private final String code;

    public TaskValidationException(String code, String message) {
        super(message);
        this.code = code;
    }

}