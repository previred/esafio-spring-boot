package com.nuevospa.app.handlers;

import com.nuevospa.app.exceptions.DataNotFoundException;
import com.nuevospa.app.models.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
        return new ResponseEntity<>(ResponseError.builder().errors(errorMessages).message("Invalid arguments error.").payload(null).build(),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseError> handleDataNotFoundException(DataNotFoundException ex) {

        return new ResponseEntity<>(
                ResponseError.builder().errors(Collections.singletonList(ex.getMessage())).message("Not found.").payload(null).build(),
                HttpStatus.NOT_FOUND);
    }
}