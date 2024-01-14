package jlillor.ms.tasks.manager.exceptions;

import jlillor.ms.tasks.manager.dtos.MsResponse;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * Manejador de excepciones.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Log4j2
@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class MsExceptionHandler extends ResponseEntityExceptionHandler {

    /** Propiedades de los mensajes. */
    private final MessageProperty messageProperty;

    // --------------------------------------------------------------------------------------
    // -- Métodos Sobreescritos -------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var message = messageProperty.getReadableError();
        log.error("handleHttpMessageNotReadable: {} messageException [{}] ", message.getMessage(), ex.getMessage());
        return new ResponseEntity<>(new MsResponse<>(message), HttpStatus.BAD_REQUEST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var message = messageProperty.getValidationError();
        final var errors = ex.getBindingResult().getFieldErrors().stream().map(e -> {
            log.error("handleMethodArgumentNotValid: {}, messageDescription [{}] ", message.getMessage(), e.getDefaultMessage());
            return e.getDefaultMessage();
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new MsResponse<>(message, errors), HttpStatus.BAD_REQUEST);
    }

    // --------------------------------------------------------------------------------------
    // -- Métodos Públicos ------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    /**
     * Maneja las excepciones de tipo {@link MissingRequestHeaderException}.
     *
     * @param ex {@link MissingRequestHeaderException} excepción.
     * @return {@link ResponseEntity} respuesta.
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<MsResponse<?>> handleMissingHeader(MissingRequestHeaderException ex) {
        final var message = String.format(messageProperty.getHeaderNotFound().getMessage(), ex.getHeaderName());
        log.error("handleMissingHeader: {} messageException [{}] ", message, ex.getMessage());
        return new ResponseEntity<>(new MsResponse<>(messageProperty.getHeaderNotFound().getCode(), message), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja las excepciones de tipo {@link MsInternalError}.
     *
     * @param exception {@link MsInternalError} excepción.
     * @return {@link MsResponse} respuesta.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MsInternalError.class})
    public MsResponse<?> handleInternarError(MsInternalError exception) {
        log.error("handleInternarError: [{}] cause {}", exception, exception.getCause());
        return new MsResponse<>(exception.getCode(), exception.getMessage());
    }

    /**
     * Maneja las excepciones de tipo {@link MsBadRequestException}.
     *
     * @param exception {@link MsBadRequestException} excepción.
     * @return {@link MsResponse} respuesta.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MsBadRequestException.class})
    public MsResponse<?> handleBadRequestError(MsBadRequestException exception) {
        log.error("handleBadPasswordUserException: [{}] cause {}", exception, exception.getCause());
        return new MsResponse<>(exception.getCode(), exception.getMessage());
    }

    /**
     * Maneja las excepciones de tipo {@link MsNotFoundException}.
     *
     * @param exception {@link MsNotFoundException} excepción.
     * @return {@link MsResponse} respuesta.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({MsNotFoundException.class})
    public MsResponse<?> handleNotFoundError(MsNotFoundException exception) {
        log.error("handleNotFoundError: [{}] cause {}", exception, exception.getCause());
        return new MsResponse<>(exception.getCode(), exception.getMessage());
    }

}
