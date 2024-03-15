package com.nuevospa.desafiospringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {
	
	private int id;
	private String username;
	@JsonIgnore
	private String password;

}
