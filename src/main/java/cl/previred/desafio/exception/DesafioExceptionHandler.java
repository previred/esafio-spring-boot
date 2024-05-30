package cl.previred.desafio.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DesafioException> handleBadCredentialsException(BadCredentialsException e) {
        String msg = "The username or password is incorrect";
        log.error(String.format("BAD CREDENTIALS: %s.", msg));
        return ResponseEntity.status(HttpStatus.valueOf(401))
                .body(DesafioException.builder()
                        .message(msg)
                        .build());
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<DesafioException> handleAccountStatusException(AccountStatusException e) {
        String msg = "The account is locked";
        log.error(String.format("ACCOUNT STATUS: %s.", msg));
        return ResponseEntity.status(HttpStatus.valueOf(403))
                .body(DesafioException.builder()
                        .message(msg)
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DesafioException> handleAccessDeniedException(AccessDeniedException e) {
        String msg = "You are not authorized to access this resource";
        log.error(String.format("ACCESS DENIED: %s.", msg));
        return ResponseEntity.status(HttpStatus.valueOf(403))
                .body(DesafioException.builder()
                        .message(msg)
                        .build());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<DesafioException> handleSignatureException(SignatureException e) {
        String msg = "The JWT signature is invalid";
        log.error(String.format("SIGNATURE: %s.", msg));
        return ResponseEntity.status(HttpStatus.valueOf(403))
                .body(DesafioException.builder()
                        .message(msg)
                        .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<DesafioException> handleExpiredJwtException(ExpiredJwtException e) {
        String msg = "The JWT token has expired";
        log.error(String.format("EXPIRED JWT: %s.", msg));
        return ResponseEntity.status(HttpStatus.valueOf(403))
                .body(DesafioException.builder()
                        .message(msg)
                        .build());
    }

}
