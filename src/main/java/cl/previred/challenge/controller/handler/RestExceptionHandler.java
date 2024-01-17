package cl.previred.challenge.controller.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestException.class})
    public ResponseEntity<Object> handleRestException(RestException restException, WebRequest request) {
        restException.printStackTrace();
        return handleExceptionInternal(restException, restException.getErrorResponse(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(RestException restException, WebRequest request) {
        restException.printStackTrace();
        return handleExceptionInternal(restException, restException.getErrorResponse(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
