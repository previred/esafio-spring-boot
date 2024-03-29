package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;
    @Mock
    private WebRequest webRequest;

    @Test
    void resourceNotFoundException_ShouldReturnNotFoundStatus() {
        // Setup
        String errorMessage = "Resource not found";
        NotFoundException notFoundException = new NotFoundException(errorMessage);
        when(webRequest.getDescription(false)).thenReturn("WebRequest description");

        // Execute
        ResponseEntity<?> response = globalExceptionHandler.resourceNotFoundException(notFoundException, webRequest);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDetails);
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), errorDetails.statusCode());
        assertEquals(errorMessage, errorDetails.message());
    }

    @Test
    void globalExceptionHandler_ShouldReturnInternalServerErrorStatus() {
        // Setup
        String errorMessage = "An error occurred";
        Exception exception = new Exception(errorMessage);
        when(webRequest.getDescription(false)).thenReturn("WebRequest description");

        // Execute
        ResponseEntity<?> response = globalExceptionHandler.globalExceptionHandler(exception, webRequest);

        // Verify
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDetails);
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDetails.statusCode());
        assertEquals(errorMessage, errorDetails.message());
    }
}
