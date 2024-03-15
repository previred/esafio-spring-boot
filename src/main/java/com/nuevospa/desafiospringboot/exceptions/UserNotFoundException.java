package com.nuevospa.desafiospringboot.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8137068183340466989L;

	public UserNotFoundException(String message) {
        super(message);
    }
}
