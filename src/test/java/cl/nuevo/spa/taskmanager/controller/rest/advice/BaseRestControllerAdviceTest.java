package cl.nuevo.spa.taskmanager.controller.rest.advice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import cl.nuevo.spa.taskmanager.domain.common.ApiBaseExceptionDetail;
import cl.nuevo.spa.taskmanager.domain.exception.BaseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class BaseRestControllerAdviceTest {

  private static final String LOCALIZED_MESSAGE = "LOCALIZED_MESSAGE";
  private static final String METHOD = "METHOD";
  private static final String URL = "URL";
  private static final String EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE";
  private static final String EXCEPTION_DEBUG_MESSAGE = "EXCEPTION_DEBUG_MESSAGE";
  private static final String ERROR_CODE = "Error_Code";
  @InjectMocks BaseRestControllerAdvice baseRestControllerAdvice;

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

  private static ApiBaseExceptionDetail buildBaseExceptionDetail(
      BaseException exception, HttpStatus httpStatus, HttpServletRequest request) {
    return ApiBaseExceptionDetail.builder()
        .debugMessage(exception.getDebugMessage())
        .message(exception.getMessage())
        .errorDetail(Arrays.toString(exception.getStackTrace()))
        .errorCode(exception.getErrorCode())
        .httpStatusCode(String.valueOf(exception.getHttpStatusCode()))
        .url(request.getRequestURL().toString())
        .method(request.getMethod())
        .localDateTime(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
        .build();
  }

  @Test
  void ShouldReturnInternalServerErrorWhenGenericExceptionHandlerItsInvoke() {
    Instant instantMock = mock(Instant.class);
    try (MockedStatic<Instant> mockedStatic = mockStatic(Instant.class)) {
      mockedStatic.when(Instant::now).thenReturn(instantMock);
      Exception exception = Mockito.mock(Exception.class);
      when(exception.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
      when(exception.getStackTrace()).thenReturn(new StackTraceElement[] {});
      HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      when(httpServletRequest.getMethod()).thenReturn(METHOD);
      when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer(URL));
      ApiBaseExceptionDetail apiBaseExceptionDetail =
          buildBaseExceptionDetail(exception, HttpStatus.INTERNAL_SERVER_ERROR, httpServletRequest);
      ResponseEntity<?> actual =
          baseRestControllerAdvice.genericExceptionHandler(exception, httpServletRequest);
      Assertions.assertEquals(apiBaseExceptionDetail, actual.getBody());
    }
  }

  @Test
  void ShouldReturnBadRequestWhenBadRequestExceptionHandlerItsInvoke() {
    Instant instantMock = mock(Instant.class);
    try (MockedStatic<Instant> mockedStatic = mockStatic(Instant.class)) {
      mockedStatic.when(Instant::now).thenReturn(instantMock);
      Exception exception = Mockito.mock(Exception.class);
      when(exception.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
      when(exception.getStackTrace()).thenReturn(new StackTraceElement[] {});
      HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      when(httpServletRequest.getMethod()).thenReturn(METHOD);
      when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer(URL));
      ApiBaseExceptionDetail apiBaseExceptionDetail =
          buildBaseExceptionDetail(exception, HttpStatus.BAD_REQUEST, httpServletRequest);
      ResponseEntity<?> actual =
          baseRestControllerAdvice.badRequestExceptionHandler(exception, httpServletRequest);
      Assertions.assertEquals(apiBaseExceptionDetail, actual.getBody());
    }
  }

  @Test
  void ShouldReturnBadRequestWhenBasicExceptionHandlerItsInvoke() {
    Instant instantMock = mock(Instant.class);
    try (MockedStatic<Instant> mockedStatic = mockStatic(Instant.class)) {
      mockedStatic.when(Instant::now).thenReturn(instantMock);
      BaseException exception = Mockito.mock(BaseException.class);
      when(exception.getMessage()).thenReturn(EXCEPTION_MESSAGE);
      when(exception.getDebugMessage()).thenReturn(EXCEPTION_DEBUG_MESSAGE);
      when(exception.getErrorCode()).thenReturn(ERROR_CODE);
      when(exception.getStackTrace()).thenReturn(new StackTraceElement[] {});
      HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      when(httpServletRequest.getMethod()).thenReturn(METHOD);
      when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer(URL));
      ApiBaseExceptionDetail apiBaseExceptionDetail =
          buildBaseExceptionDetail(exception, HttpStatus.BAD_REQUEST, httpServletRequest);
      ResponseEntity<?> actual =
          baseRestControllerAdvice.basicExceptionHandler(exception, httpServletRequest);
      Assertions.assertEquals(apiBaseExceptionDetail, actual.getBody());
    }
  }
}
