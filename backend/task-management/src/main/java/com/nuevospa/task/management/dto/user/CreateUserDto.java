package com.nuevospa.task.management.dto.user;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nuevospa.task.management.validator.EmailValidator;
import com.nuevospa.task.management.validator.PasswordValidator;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserDto {
	
	@Size(min = 2, max = 50, message = "El campo 'name' puede contener un largo máximo de 50 y mínimo 2.")
	private String name;
	
	@EmailValidator
	@Size(max = 50, message = "El campo 'email' puede contener un largo máximo de 50.")
	private String email;
	
	@PasswordValidator
	@Size(min = 8, max = 12, message = "El campo 'password' puede tener un largo máximo de 12 y mínimo 8.")
	private String password;
	
	
	
}
