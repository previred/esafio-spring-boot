package co.moveapps.spa.core.controller;

import co.moveapps.spa.core.controller.dto.BaseResponse;
import co.moveapps.spa.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

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
        log.error("ExceptionHandler - handleDefaultExceptions: ", exception);

        BaseResponse response = BaseResponse.builder(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }
}
