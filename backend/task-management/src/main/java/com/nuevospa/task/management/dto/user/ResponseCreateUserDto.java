package com.nuevospa.task.management.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateUserDto {
	
	
	private String name;
	
	private String email;
	
	private LocalDateTime created;
	
	private LocalDateTime lastLogin;
	
	@JsonProperty("isActive")
	private boolean isActive;

}
