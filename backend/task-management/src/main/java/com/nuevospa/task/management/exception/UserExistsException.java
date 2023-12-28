package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class UserExistsException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public UserExistsException(String message) {
		super(message);

	}

	public UserExistsException(String message, int code) {
		super(message);
		this.code = code;

	}

	public UserExistsException(Throwable cause) {
		super(cause);
	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
