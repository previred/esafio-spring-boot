package com.nuevospa.task.management.exception;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException{

	private static final long serialVersionUID = -2765315669291152069L;

	private int code;

	public TokenException(String message) {
		super(message);

	}

	public TokenException(String message, int code) {
		super(message);
		this.code = code;

	}

	public TokenException(Throwable cause) {
		super(cause);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}
		

}
