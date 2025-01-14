package com.previred.gestion_tareas_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.previred.gestion_tareas_api.dtos.ApiResponseDTO;


@RestControllerAdvice
public class GlobalExceptionHandler {
        
        @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponseDTO> handleAuthenticationException(AuthenticationException ex) {  
        ApiResponseDTO response = new ApiResponseDTO(false, "No estás autenticado. Por favor, inicia sesión." , ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
}
        
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        ApiResponseDTO response = new ApiResponseDTO(false, "Acceso denegado: " , ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
}

        @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO> handleRuntimeException(RuntimeException ex) {
        ApiResponseDTO response = new ApiResponseDTO(false, "Error de ejecución: ", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleGenericException(Exception ex) {
        ApiResponseDTO response = new ApiResponseDTO(false, "Ocurrió un error inesperado.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
