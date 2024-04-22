package com.company.handlers;

import com.company.exception.AppException;
import com.company.model.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Reflects exceptions generic logic related
 *
 * @author carvajal
 * @version 1.0
 * @since 2018-12-07
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handlingApplicationException(
            final HttpServletRequest request,
            final AppException exception) {
        return new ResponseEntity<>(ErrorResponse
                                    .builder()
                                    .codError(exception.getCodError())
                                    .messageError(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlingGenericException(
            final HttpServletRequest request, final Exception exception) {
        log.error("error processing generic exception on request", exception);
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .codError("GENERAL-ERROR")
                .messageError(exception.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handlingGenericException(
            final HttpServletRequest request, final RuntimeException exception) {
        log.error("error processing generic exception on request", exception);
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .codError("GENERAL-ERROR")
                .messageError(exception.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
