package com.test.previred.infrastructure.rest.controller.common;


import com.test.previred.domain.model.common.ObjectTechMsg;
import com.test.previred.domain.model.common.OperationException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class GenericControllerOperations {
    private static final Logger logger = LogManager.getLogger(GenericControllerOperations.class);


    private final Commons commons = new Commons();

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Response<Map<String, String>> response = new Response<>();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.setData(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.getMessage().add(getCommons().getTypicalErrors().get("400"));
        return response;
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<Response<Object>> methodArgumentNotValidException(OperationException e) {
        Response<Object> responseException = new Response<>();
        responseException.setStatus(HttpStatus.resolve(e.getHttpStatus()));
        responseException.setMessage(Collections.singletonList(e.getMessage()));
        return ResponseEntity.status(e.getHttpStatus()).body(responseException);
    }

    public void generateLogInfo(String actionName, String serviceName, Object message) {
        logger.info(ObjectTechMsg.builder()
                .actionName(actionName)
                .serviceName(serviceName)
                .message(message)
                .build());
    }

    public void generateLogError(String actionName, String serviceName, Object message) {
        logger.error(ObjectTechMsg.builder()
                .actionName(actionName)
                .serviceName(serviceName)
                .message(message)
                .build());
    }
}