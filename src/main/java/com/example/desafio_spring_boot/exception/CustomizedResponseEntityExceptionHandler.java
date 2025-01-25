package com.example.desafio_spring_boot.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.desafio_spring_boot.domain.status_task.StatusNames;
import com.example.desafio_spring_boot.domain.task.TaskNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(Exception.class)
        public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request)
                        throws Exception {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                                request.getDescription(false));

                return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        @ExceptionHandler(TaskNotFoundException.class)
        public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
                        throws Exception {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                ex.getMessage(), request.getDescription(false));

                return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                        @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
                        @NonNull HttpStatusCode status, @NonNull WebRequest request) {

                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                ex.getFieldError().getDefaultMessage(), request.getDescription(false));

                return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(
                        @NonNull HttpMessageNotReadableException ex, @NonNull HttpHeaders headers,
                        @NonNull HttpStatusCode status,
                        @NonNull WebRequest request) {
                String allowedValues = StatusNames.allowedValues();
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                "Tienes un valor equivocado para el estado de la tarea. Valores permitidos: "
                                                + allowedValues,
                                request.getDescription(false));

                return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(BadCredentialsException.class)
        public final ResponseEntity<ErrorDetails> handleBadCredentialsException(
                        BadCredentialsException ex, WebRequest request) {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                "Credenciales incorrectas", request.getDescription(false));

                return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);
        }

}
