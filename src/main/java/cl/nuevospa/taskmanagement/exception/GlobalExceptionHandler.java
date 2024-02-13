package cl.nuevospa.taskmanagement.exception;

import cl.nuevospa.taskmanagement.dto.MessageResponseDTO;
import cl.nuevospa.taskmanagement.util.MessagesConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getCurrentTimestamp() {
            return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO handleUserNotFoundException(UserNotFoundException e) {
        log.error("User not found: {}", e.getMessage());
        return MessageResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .timestamp(getCurrentTimestamp())
                .message(MessagesConstants.USER_NOT_FOUND + e.getMessage())
                .build();
    }

    @ExceptionHandler(TaskStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponseDTO handleTaskStateException(TaskStateException e) {
        log.error("Task state error: {}", e.getMessage());
        return MessageResponseDTO.builder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timestamp(getCurrentTimestamp())
                .message(MessagesConstants.TASK_STATE_NOT_FOUND + e.getMessage())
                .build();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO handleTaskNotFoundException(TaskNotFoundException e) {
        log.error("Task not found: {}", e.getMessage());
        return MessageResponseDTO.builder()
                .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .timestamp(getCurrentTimestamp())
                .message(e.getMessage())
                .build();
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponseDTO handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Error in argument: {}", e.getMessage());
        return MessageResponseDTO.builder()
                .message(MessagesConstants.NOT_VALID_ARGUMENT + e.getMessage())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageResponseDTO handleCustomBadCredentialsException(BadCredentialsException e) {
        log.error("Authentication error: {}", e.getMessage());
        return MessageResponseDTO.builder()
                .code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                .timestamp(getCurrentTimestamp())
                .message(MessagesConstants.INVALID_CREDENTIALS)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageResponseDTO handleException(Exception e) {
        log.error("An error occurred ", e);
        return MessageResponseDTO.builder()
                .message(MessagesConstants.INTERNAL_SERVER_ERROR_MESSAGE)
                .build();
    }


}