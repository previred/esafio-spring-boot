package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class ValidationPriorityException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public ValidationPriorityException(String message) {
		super(message);

	}

	public ValidationPriorityException(String message, int code) {
		super(message);
		this.code = code;

	}

	public ValidationPriorityException(Throwable cause) {
		super(cause);
	}

	public ValidationPriorityException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
