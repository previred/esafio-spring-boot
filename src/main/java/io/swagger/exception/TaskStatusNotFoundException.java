package io.swagger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskStatusNotFoundException extends RuntimeException {

    public TaskStatusNotFoundException(Long id) {
        super(String.format("Task status with id %d not found", id));
    }

}
