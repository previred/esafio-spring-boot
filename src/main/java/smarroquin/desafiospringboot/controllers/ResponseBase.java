package smarroquin.desafiospringboot.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class ResponseBase {
	
	private boolean result;
	private String message;

}