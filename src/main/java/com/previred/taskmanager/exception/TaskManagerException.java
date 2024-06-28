package com.previred.taskmanager.exception;

import java.io.Serial;

public class TaskManagerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TaskManagerException(String message) {
        super(message);
    }

    public TaskManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
