package com.desafio.spring.error;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class AdviceController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exceptionError(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().get();
        return new ResponseEntity<>(
                buildErrorResponse(objectError.getDefaultMessage(), e.getClass().getSimpleName()),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> exceptionError(UnauthorizedException e) {
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionError(UsernameNotFoundException e) {
        return new ResponseEntity<>(
                buildErrorResponse("Access Denied", e.getClass().getSimpleName()),
                HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> exceptionError(ForbiddenException e) {
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> exceptionError(BadRequestException e) {
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionError(NotFoundException e) {
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionError(EntityNotFoundException e) {
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> exceptionError(ConflictException e) {
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionError(Exception e) {
        //log.error("Exception :: " , e);
        return new ResponseEntity<>(
                buildErrorResponse(e.getMessage(), e.getClass().getSimpleName()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse buildErrorResponse(String message, String exception) {
        ErrorResponse response = new ErrorResponse();
        response.message(message);
        response.exception(exception);
        return response;
    }
}
