package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class BadPasswordUserException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public BadPasswordUserException(String message) {
		super(message);

	}

	public BadPasswordUserException(String message, int code) {
		super(message);
		this.code = code;

	}

	public BadPasswordUserException(Throwable cause) {
		super(cause);
	}

	public BadPasswordUserException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
