package com.previred.nspa.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private HttpStatus status;
    private String message;
    private String details;

    public ErrorDetails(HttpStatus status, String message, String details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }

}
