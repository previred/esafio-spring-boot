package com.nuevospa.task.management.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"created", "lastLogin", "token", "isActive", "name", "email"})
public class ResponseLoginUserDto implements Serializable {
	
	    private static final long serialVersionUID = -1115084710226783484L;
		
	    private LocalDateTime created;	    
	    private LocalDateTime lastLogin;    
	    private String token;
	    @JsonProperty("isActive")
	    private boolean isActive;  
	    private String name;	
	    private String email;
	   

}
