package ar.com.challenge.desafio_spring_boot.config;

import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;
import ar.com.challenge.desafio_spring_boot.response.Response;
import ar.com.challenge.desafio_spring_boot.response.Error;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            ExpiredJwtException.class,
            SignatureException.class,
            MalformedJwtException.class,
            UnsupportedJwtException.class,
            IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response<Object, Error> handleJwtException(final Exception ex) {
        return Response.error(
                Error.builder()
                        .code("JWT_EXCEPTION")
                        .error(ex.getClass().getSimpleName())
                        .cause(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response<Object, Error> handleNotFoundException(final ResourceNotFoundException ex) {
        return Response.error(
                Error.builder()
                        .code(ResourceNotFoundException.CODE)
                        .error(ex.getClass().getSimpleName())
                        .cause("Incorrect parameters: Entity not available")
                        .build());
    }
    @ExceptionHandler(value = {ResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response<Object, Error> handleFoundException(final ResourceFoundException ex) {
        return Response.error(
                Error.builder()
                        .code(ResourceNotFoundException.CODE)
                        .error(ex.getClass().getSimpleName())
                        .cause("Incorrect parameters: Entity exist")
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Object, Error> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        var errorMessage = String.format("Invalid argument type: %s should be %s",
                ex.getName(),
                Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        return Response.error(
                Error.builder()
                        .code(ex.getErrorCode())
                        .error(ex.getClass().getSimpleName())
                        .cause(errorMessage)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Response<Object, Error> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex);
        return Response.error(
                Error.builder()
                        .code("NOT_VALID_ARGUMENT")
                        .error(ex.getClass().getSimpleName())
                        .cause("Invalid argument")
                        .build());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Object, Error> handleUncaughtException(final Exception ex) {
        return Response.error(
                Error.builder()
                        .code("UnCaughtException")
                        .error(ex.getClass().getSimpleName())
                        .cause(ex.getMessage())
                        .build());
    }
}
