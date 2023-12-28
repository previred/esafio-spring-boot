package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class TaskStatusNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public TaskStatusNotFoundException(String message) {
		super(message);

	}

	public TaskStatusNotFoundException(String message, int code) {
		super(message);
		this.code = code;

	}

	public TaskStatusNotFoundException(Throwable cause) {
		super(cause);
	}

	public TaskStatusNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
