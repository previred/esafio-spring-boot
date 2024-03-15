package com.nuevospa.desafiospringboot.exceptions;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8137068183340466989L;

	public TaskNotFoundException(String message) {
        super(message);
    }
}
