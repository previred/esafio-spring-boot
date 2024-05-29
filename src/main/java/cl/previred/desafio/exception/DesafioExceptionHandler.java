package cl.previred.desafio.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DesafioExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DesafioException> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(String.format("BAD REQUEST: %s.", e.getMessage()));
        return ResponseEntity.badRequest().body(DesafioException.builder()
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DesafioException> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(String.format("NOT FOUND: %s.", e.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DesafioException.builder()
                        .message(e.getMessage())
                        .build());
    }
}
