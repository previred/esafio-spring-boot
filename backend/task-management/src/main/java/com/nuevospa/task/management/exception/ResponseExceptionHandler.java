package com.nuevospa.task.management.exception;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ UserExistsException.class })
	public final ResponseErrorsException handleUserExistsException(UserExistsException exception) {
		log.error("handleUserExistsException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ UserException.class })
	public final ResponseErrorsException handleUserExistsException(UserException exception) {
		log.error("handleUserException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ UserNotFoundException.class })
	public final ResponseErrorsException handleUserNotFoundException(UserNotFoundException exception) {
		log.error("handleUserNotFoundException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ TokenException.class })
	public final ResponseErrorsException handleTokenException(TokenException exception) {
		log.error("handleTokenException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ BadPasswordUserException.class })
	public final ResponseErrorsException handleBadPasswordUserException(BadPasswordUserException exception) {
		log.error("handleBadPasswordUserException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	
	
	private List<Error> mapperError(int cod, String messageException){
		List<Error> errors = new ArrayList<>();
		errors.add(new Error(LocalDateTime.now(), cod, messageException));
		
		return errors;
		
	}
	
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
		List<Error> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(exp -> new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exp.getDefaultMessage()))
                .collect(Collectors.toList());
		
		errors.forEach( e -> {
			log.error("handleMethodArgumentNotValid: code {} [{}] ", e.getCodigo(), e.getDetail());
		});
				

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
	
	
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = "Ha ocurrido un error, revisar datos de entrada";
		
		log.error("handleHttpMessageNotReadable: {} messageException [{}] ", message, ex.getMessage());
        return new ResponseEntity<>(mapperError(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST); 
    }
	
	@ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> handleMissingHeader(MissingRequestHeaderException ex) {
        String headerName = ex.getHeaderName();
        String message = headerName + " no se encuentra configurado en el header.";
       

        return new ResponseEntity<>(mapperError(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ ValidationPriorityException.class })
	public final ResponseErrorsException handleValidationPriorityException(ValidationPriorityException exception) {
		log.error("handleValidationPriorityException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ TaskStatusNotFoundException.class })
	public final ResponseErrorsException handleTaskStatusNotFoundException(TaskStatusNotFoundException exception) {
		log.error("handleTaskStatusNotFoundException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ TaskException.class })
	public final ResponseErrorsException handleTaskException(TaskException exception) {
		log.error("handleTaskException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ TaskNotFoundException.class })
	public final ResponseErrorsException handleTaskNotFoundException(TaskNotFoundException exception) {
		log.error("handleTaskNotFoundException: [{}] cause {}", exception, exception.getCause());
		
		return new ResponseErrorsException(mapperError(exception.getCode(), exception.getMessage()));
	}
	

}
