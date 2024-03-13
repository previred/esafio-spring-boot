package cl.gestiontareasprevired.exception;

import cl.gestiontareasprevired.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return new ResponseEntity<>(new ErrorResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TareaDuplicadaException.class)
    public ResponseEntity<Object> handleTareaDuplicada(TareaDuplicadaException ex) {
        return new ResponseEntity<>(new ErrorResponse(false,ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ErrorIngresoException.class)
    public ResponseEntity<Object> handleErrorIngresoException(ErrorIngresoException ex) {
        return new ResponseEntity<>(new ErrorResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(false,"Se ha producido un error en el servidor."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        StringBuilder errorMessage = new StringBuilder("Se ha producido un error de validación: ");

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            if (errorMessage.length() > "Se ha producido un error de validación: ".length()) {
                errorMessage.append("; ");
            }
            errorMessage.append(fieldName).append(": ").append(message);
        });

        ErrorResponse errorResponse = new ErrorResponse(false, errorMessage.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}