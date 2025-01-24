package cl.previred.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StatusNotFoundException(String message) {
        super(message);
    }
}
