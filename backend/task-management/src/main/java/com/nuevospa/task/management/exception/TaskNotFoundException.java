package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public TaskNotFoundException(String message) {
		super(message);

	}

	public TaskNotFoundException(String message, int code) {
		super(message);
		this.code = code;

	}

	public TaskNotFoundException(Throwable cause) {
		super(cause);
	}

	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
