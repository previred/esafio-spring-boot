package com.desafio.gestion.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<?> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(new Date())
                        .message(e.getMessage())
                        .description(request.getDescription(false)).build());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleINoSuchElementException(NoSuchElementException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .timestamp(new Date())
                        .message(e.getMessage())
                        .description(request.getDescription(false)).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        String errorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(new Date())
                        .message(errorMessage)
                        .description(request.getDescription(false)).build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .timestamp(new Date())
                        .message(e.getMessage())
                        .description(request.getDescription(false)).build());
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorMessage> handleLockedException(LockedException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .timestamp(new Date())
                        .message(e.getMessage())
                        .description(request.getDescription(false)).build());
    }
}