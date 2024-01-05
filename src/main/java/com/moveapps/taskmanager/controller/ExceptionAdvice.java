package com.moveapps.taskmanager.controller;

import com.moveapps.taskmanager.common.ErrorCode;
import com.moveapps.taskmanager.common.dto.ErrorDTO;
import com.moveapps.taskmanager.common.exception.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDTO> taskNotFoundError(TaskNotFoundException taskNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorDTO.builder()
                .message(taskNotFoundException.getMessage())
                .code(ErrorCode.TASK_NOT_FOUND.getValue()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> generalError(Exception exception){
        log.error("Unexpected error = {}", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorDTO.builder()
                .message("Internal Error, contact support")
                .code(ErrorCode.GENERAL.getValue()).build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> invalidCredentials(BadCredentialsException badCredentialsException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorDTO.builder()
                .code(ErrorCode.INVALID_CREDENTIALS.getValue())
                .message("Invalid credentials")
                .build());
    }


}
