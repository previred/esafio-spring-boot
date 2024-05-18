package co.com.task.api.exceptions;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.task.api.dto.ErrorResponseDTO;
import co.com.task.api.exceptions.ExceptionManager.EmptyFieldException;
import co.com.task.api.exceptions.ExceptionManager.LoginException;
import co.com.task.api.exceptions.ExceptionManager.NotFoundException;
import co.com.task.api.exceptions.ExceptionManager.NotValidParamException;
import co.com.task.api.exceptions.ExceptionManager.SaveException;
import co.com.task.api.exceptions.ExceptionManager.UpdateException;
import co.com.task.api.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handlerAccessDeniedException(final Exception ex,
	    final HttpServletRequest request,
	    final HttpServletResponse response) {
	return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.FORBIDDEN.value()).message(HttpStatus.FORBIDDEN.name())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.BAD_REQUEST.value())
			.message("Mensaje con formato no valido, validar parametros")
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionLogin(LoginException exception) {
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.UNAUTHORIZED.value())
			.message(HttpStatus.UNAUTHORIZED.name())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception exception) {
	log.error("exceptionHandler", exception);
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.message(HttpStatus.INTERNAL_SERVER_ERROR.name())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(NotValidParamException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionNotValidParam(NotValidParamException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionEmptyField(EmptyFieldException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionNotFoundData(NotFoundException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.BAD_REQUEST.value())
			.message(HttpStatus.BAD_REQUEST.name())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionSave(SaveException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.BAD_REQUEST.value())
			.message(exception.getMessage())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(UpdateException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionUpdate(UpdateException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionUsernameNotFound(UsernameNotFoundException exception) {
	return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.FORBIDDEN.value())
			.message("El usuario ".concat(exception.getMessage()).concat(" asociado al token no existe"))
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponseDTO> exceptionInternal() {
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(APPLICATION_JSON)
		.body(ErrorResponseDTO.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.message(HttpStatus.INTERNAL_SERVER_ERROR.name())
			.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now()))
			.build());
    }

}
