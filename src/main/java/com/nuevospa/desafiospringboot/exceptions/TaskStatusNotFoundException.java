package com.nuevospa.desafiospringboot.exceptions;

public class TaskStatusNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8137068183340466989L;

	public TaskStatusNotFoundException(String message) {
        super(message);
    }
}
