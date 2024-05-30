package com.nuevospa.taskmanager.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException exception) {

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(APPLICATION_JSON)
                        .body(Map.of("mensaje", exception.getException().getName()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(Map.of("mensaje", exception.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> internalErrorHandler() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(Map.of("mensaje", "Error inesperado"));
    }

}
