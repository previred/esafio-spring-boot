package com.nuevospa.task.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.task.management.dto.response.ResponseService;
import com.nuevospa.task.management.dto.user.CreateUserDto;
import com.nuevospa.task.management.dto.user.LoginUser;
import com.nuevospa.task.management.dto.user.ResponseCreateUserDto;
import com.nuevospa.task.management.dto.user.ResponseLoginUserDto;
import com.nuevospa.task.management.services.user.IUserService;
import com.nuevospa.task.management.util.CodeResponse;
import com.nuevospa.task.management.util.MessageResponse;


@RestController
public class UserController {
	
	@Autowired
    private IUserService service;
	
	@PostMapping("/create-user")
	public ResponseEntity<ResponseService<ResponseCreateUserDto>> createUser(@Valid @RequestBody CreateUserDto createUser, @RequestHeader("token") String token){
		
		
		ResponseCreateUserDto response = service.createUser(createUser, token);
		
		ResponseService<ResponseCreateUserDto> resp = 
				new ResponseService<>(CodeResponse.SUCCESS_CODE, MessageResponse.USER_SUCCESS_MESSAGE, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<ResponseService<ResponseLoginUserDto>> loginUser(@Valid @RequestBody LoginUser user){
		
		
		ResponseLoginUserDto response = service.loginUser(user);
		
		ResponseService<ResponseLoginUserDto> resp = 
				new ResponseService<>(CodeResponse.SUCCESS_CODE, MessageResponse.LOGIN_SUCCESS_MESSAGE, response);
		
		return ResponseEntity.status(HttpStatus.OK).body(resp);
		
	}

}
