package com.nuevoapp.prueba.config.error.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class CustomError {

    private HttpStatus status;
    private int code;
    private String timestamp;
    private String message;
    private List<String> errors;
}
