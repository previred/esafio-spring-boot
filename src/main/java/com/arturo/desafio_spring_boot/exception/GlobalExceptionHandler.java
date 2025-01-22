package com.arturo.desafio_spring_boot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.arturo.desafio_spring_boot.helpers.ErrorResponseObjectHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseObjectHelper errorResponseObjectHelper;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return ResponseEntity.status(ex.getStatusCode()).headers(headers).body(
                this.errorResponseObjectHelper.toJsonString(
                    ex.getStatusCode().value(),
                    ex.getReason(),
                    ex.getMessage()
                )
            );
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(
                this.errorResponseObjectHelper.toJsonString(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    ex.getLocalizedMessage(),
                    ex.getMessage()
                )
            );
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}

