package com.previred.desafiobackend.domain.exception.task;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }

    public static TaskNotFoundException thrown(){
        throw new TaskNotFoundException("Task Not Found.");
    }
}
