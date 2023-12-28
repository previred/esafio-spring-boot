package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public UserNotFoundException(String message) {
		super(message);

	}

	public UserNotFoundException(String message, int code) {
		super(message);
		this.code = code;

	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
