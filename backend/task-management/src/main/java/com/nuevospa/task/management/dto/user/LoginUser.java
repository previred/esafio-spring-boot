package com.nuevospa.task.management.dto.user;

import javax.validation.constraints.Size;

import com.nuevospa.task.management.validator.EmailValidator;
import com.nuevospa.task.management.validator.PasswordValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
	
	@EmailValidator
	@Size(max = 50, message = "El campo 'email' puede contener un largo máximo de 50.")
	private String email;
	
	@PasswordValidator
	@Size(min = 8, max = 12, message = "El campo 'password' puede tener un largo máximo de 12 y mínimo 8.")
	private String password;

}
