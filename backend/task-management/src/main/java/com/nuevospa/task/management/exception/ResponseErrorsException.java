package com.nuevospa.task.management.exception;

import java.util.List;

import lombok.Data;

@Data
public class ResponseErrorsException {
	
	private List<Error> error;
	
	public ResponseErrorsException(List<Error> error) {	
		this.error = error;
	}

}
