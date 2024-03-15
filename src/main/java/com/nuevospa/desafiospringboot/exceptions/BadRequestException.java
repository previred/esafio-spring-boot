package com.nuevospa.desafiospringboot.exceptions;

public class BadRequestException extends IllegalArgumentException {

	private static final long serialVersionUID = 7216883218372028087L;

	public BadRequestException(String message) {
        super(message);
    }
}
