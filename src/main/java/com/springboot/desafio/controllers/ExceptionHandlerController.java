package com.springboot.desafio.controllers;

import com.springboot.desafio.exceptions.LoginException;
import com.springboot.desafio.exceptions.TareaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.springboot.desafio.constants.Constantes.CODIGO_ERROR;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(TareaException.class)
    public ResponseEntity<Object> handleCrearTareaException(TareaException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("codigo", CODIGO_ERROR);
        body.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("codigo", CODIGO_ERROR);
        body.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }


}
