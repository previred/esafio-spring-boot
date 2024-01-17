package com.nuevospa.taskcontrol.handler;

import com.nuevospa.taskcontrol.dtos.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleEmailValidationError(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setFechaError(LocalDateTime.now());
        errorResponse.setDetalle(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
