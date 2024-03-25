package smarroquin.desafiospringboot.controllers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends ResponseBase {
	
	private String token;
	
	public LoginResponse(String token, boolean result, String message) {
		super(result, message);
		this.token = token;
	}
}