package com.previred.challenge.controllers;

import com.previred.challenge.dto.JSONError;
import com.previred.challenge.dto.JsonMessageDTO;
import com.previred.challenge.exceptions.TaskNoAccessException;
import com.previred.challenge.exceptions.TaskNotFoundException;
import com.previred.challenge.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var jsonError = new JSONError();
        for (var fieldError : e.getBindingResult().getFieldErrors()) {
            jsonError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return jsonError;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public JsonMessageDTO handleUserNotFoundException(UserNotFoundException e) {
        log.error("User not found {}", e.getUsername());
        return new JsonMessageDTO(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public JsonMessageDTO handleTaskNotFoundException(TaskNotFoundException e) {
        log.error("Task not found {}", e.getId());
        return new JsonMessageDTO(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TaskNoAccessException.class)
    public JsonMessageDTO handleTaskNoAccessException(TaskNoAccessException e) {
        log.error("User {} permission denied for task {}", e.getUserId(), e.getTaskId());
        return new JsonMessageDTO(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

}
