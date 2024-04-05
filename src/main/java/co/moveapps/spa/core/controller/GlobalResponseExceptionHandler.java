package co.moveapps.spa.core.controller;

import co.moveapps.spa.core.controller.dto.BaseResponse;
import co.moveapps.spa.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        if (Optional.ofNullable(ex).map(BusinessException::getResponse).isPresent())
            return new ResponseEntity<>(ex.getResponse(), ex.getResponse().getStatus());

        BaseResponse response = BaseResponse.builder(HttpStatus.BAD_REQUEST, ex.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }


    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleDefaultExceptions(Exception exception, WebRequest request) {
        BaseResponse response = BaseResponse.builder(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponse response;
        if (exception instanceof MethodArgumentNotValidException) {

            String errors = ((MethodArgumentNotValidException) exception).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(m -> m.getField() + " " + m.getDefaultMessage())
                    .collect(Collectors.joining(" => "));

            response = BaseResponse.builder(HttpStatus.BAD_REQUEST, errors).build();
            return new ResponseEntity<>(response, response.getStatus());
        }

        response = BaseResponse.builder(status, exception.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }
}
