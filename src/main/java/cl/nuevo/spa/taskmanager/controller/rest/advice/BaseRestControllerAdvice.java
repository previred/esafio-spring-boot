package cl.nuevo.spa.taskmanager.controller.rest.advice;

import cl.nuevo.spa.taskmanager.domain.common.ApiBaseExceptionDetail;
import cl.nuevo.spa.taskmanager.domain.exception.BaseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseRestControllerAdvice {

  private static ApiBaseExceptionDetail buildBaseExceptionDetail(
      Exception exception, HttpStatus httpStatus, HttpServletRequest request) {
    return ApiBaseExceptionDetail.builder()
        .debugMessage(exception.getLocalizedMessage())
        .message(httpStatus.getReasonPhrase())
        .errorDetail(Arrays.toString(exception.getStackTrace()))
        .errorCode(String.valueOf(httpStatus.value()))
        .httpStatusCode(String.valueOf(httpStatus.value()))
        .url(request.getRequestURL().toString())
        .method(request.getMethod())
        .localDateTime(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
        .build();
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<?> genericExceptionHandler(
      Exception exception, HttpServletRequest request) {
    ApiBaseExceptionDetail apiBaseExceptionDetail =
        buildBaseExceptionDetail(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiBaseExceptionDetail);
  }

  @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<?> badRequestExceptionHandler(
      Exception exception, HttpServletRequest request) {
    ApiBaseExceptionDetail apiBaseExceptionDetail =
        buildBaseExceptionDetail(exception, HttpStatus.BAD_REQUEST, request);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiBaseExceptionDetail);
  }

  @ExceptionHandler({BaseException.class})
  public ResponseEntity<?> basicExceptionHandler(
      BaseException exception, HttpServletRequest request) {
    ApiBaseExceptionDetail apiBaseExceptionDetail =
        ApiBaseExceptionDetail.builder()
            .debugMessage(exception.getDebugMessage())
            .message(exception.getMessage())
            .errorDetail(Arrays.toString(exception.getStackTrace()))
            .errorCode(exception.getErrorCode())
            .httpStatusCode(String.valueOf(exception.getHttpStatusCode()))
            .url(request.getRequestURL().toString())
            .method(request.getMethod())
            .localDateTime(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
            .build();
    return ResponseEntity.status(exception.getHttpStatusCode()).body(apiBaseExceptionDetail);
  }
}
