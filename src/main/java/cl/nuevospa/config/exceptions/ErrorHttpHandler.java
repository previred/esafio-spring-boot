package cl.nuevospa.config.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.nuevospa.config.exceptions.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHttpHandler {
    
    private Integer NOT_FOUND=404;

    private Integer BAD_REQUEST=400;
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException nfe, HttpServletRequest request){
        log.error(nfe.getMessage());
        return new ResponseEntity<>(buildErrorResponse(nfe.getMessage(),NOT_FOUND), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre, HttpServletRequest request){
        log.error(bre.getMessage());
        return new ResponseEntity<>(buildErrorResponse(bre.getMessage(),BAD_REQUEST), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ErrorResponse buildErrorResponse(String message, Integer code) {
      
        return ErrorResponse.builder()
                .message(message)
                .code(code)
                .build();
    }
}
