package com.nuevospa.desafiospringboot.exceptions;

public class PaswordNotValidException extends IllegalArgumentException {

	private static final long serialVersionUID = 7216883218372028087L;

	public PaswordNotValidException(String message) {
        super(message);
    }
}
