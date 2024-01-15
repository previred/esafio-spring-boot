package com.nuevoapp.prueba.config.error;

import com.nuevoapp.prueba.config.error.dto.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementExistsException (
            NoSuchElementException ex){
        CustomError error = CustomError.builder()
                .status(HttpStatus.NO_CONTENT)
                .message(ex.getMessage())
                .code(HttpStatus.NO_CONTENT.value())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
                .build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex){
        CustomError error = CustomError.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .code(HttpStatus.CONFLICT.value())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
                .build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(
            IllegalArgumentException ex){
        CustomError error = CustomError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
                .build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(
            AuthenticationException ex){
        CustomError error = CustomError.builder()
                .status(HttpStatus.FORBIDDEN)
                .message("Invalid credentials")
                .code(HttpStatus.FORBIDDEN.value())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
                .build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler({MappingException.class})
    public ResponseEntity<Object> handleIMappingException(
            MappingException ex){
        CustomError error = CustomError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
                .build();
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(CustomError ex) {
        log.error("Error status={} message={} ", ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(ex, ex.getStatus());
    }
}
