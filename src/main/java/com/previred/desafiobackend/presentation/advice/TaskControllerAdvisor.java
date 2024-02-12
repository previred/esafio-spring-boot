package com.previred.desafiobackend.presentation.advice;

import com.previred.desafiobackend.domain.dto.error.ApiError;
import com.previred.desafiobackend.domain.exception.task.NonValidStatusChangeException;
import com.previred.desafiobackend.domain.exception.task.TaskNotFoundException;
import com.previred.desafiobackend.domain.exception.user.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@ControllerAdvice
@Log4j2
public class TaskControllerAdvisor {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handleTaskNotFoundException(TaskNotFoundException exception) {
        log.info("[handleTaskNotFoundException] Handling TaskNotFoundException");
        return ResponseEntity.badRequest().body(ApiError.builder()
                .message(exception.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(NonValidStatusChangeException.class)
    public ResponseEntity<ApiError> handleNonValidStatusChangeException(NonValidStatusChangeException exception) {
        log.info("[handleUserNotFoundException] Handling UserNotFoundException");
        return ResponseEntity.badRequest().body(ApiError.builder()
                .message(exception.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException exception) {
        log.info("[handleNonValidStatusChangeException] Handling NonValidStatusChangeException");
        return ResponseEntity.badRequest().body(ApiError.builder()
                .message(exception.getMessage()).timestamp(LocalDateTime.now()).build());
    }


}
