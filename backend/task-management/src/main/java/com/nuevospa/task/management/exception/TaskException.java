package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class TaskException extends RuntimeException{

	private static final long serialVersionUID = 611909322158930120L;
	
	private int code;
	
	public TaskException() {
		super();

	}

	public TaskException(String message) {
		super(message);

	}
	
	public TaskException(String message, int code) {
		super(message);
		this.code = code;

	}

	public TaskException(Throwable cause) {
		super(cause);
	}

	public TaskException(String message, Throwable cause) {
		super(message, cause);
	
	}
	
	public TaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}


}
