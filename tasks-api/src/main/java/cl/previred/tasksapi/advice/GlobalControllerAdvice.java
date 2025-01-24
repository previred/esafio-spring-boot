package cl.previred.tasksapi.advice;

import cl.previred.tasksapi.dto.ErrorDetails;
import cl.previred.tasksapi.exceptions.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {
    private static final String UNEXPECTED_EXCEPTION_MESSAGE="Ocurrió un error inesperado ";
    private static final String BAD_CREDENTIALS_EXCEPTION_MESSAGE="Credenciales incorrectas";

    // Manejar excepciones personalizadas
    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ErrorDetails> handleCustomException(TaskException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).message(ex.getMessage()).details(request.getDescription(false)).build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Error de autenticación
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDetails> handleCustomException(AuthenticationException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).message(BAD_CREDENTIALS_EXCEPTION_MESSAGE).details(request.getDescription(false)).build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Manejar excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).message(UNEXPECTED_EXCEPTION_MESSAGE.concat("(").concat(ex.getMessage()).concat(")")).details(request.getDescription(false)).build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
