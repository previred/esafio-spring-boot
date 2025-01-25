package com.example.desafio_spring_boot.exception;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorDetails {
    private final LocalDateTime timestamp;
    private final String message;
    private final String details;

}
