package com.nuevospa.task.management.services.user;

import com.nuevospa.task.management.dto.user.CreateUserDto;
import com.nuevospa.task.management.dto.user.LoginUser;
import com.nuevospa.task.management.dto.user.ResponseCreateUserDto;
import com.nuevospa.task.management.dto.user.ResponseLoginUserDto;

public interface IUserService {
	
	ResponseCreateUserDto createUser(CreateUserDto reqCreateUsuario, String token);
	
	ResponseLoginUserDto loginUser(LoginUser user);

}
