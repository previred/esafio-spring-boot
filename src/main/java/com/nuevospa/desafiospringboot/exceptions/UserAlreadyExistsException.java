package com.nuevospa.desafiospringboot.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 7216883218372028087L;

	public UserAlreadyExistsException(String message) {
        super(message);
    }
}
