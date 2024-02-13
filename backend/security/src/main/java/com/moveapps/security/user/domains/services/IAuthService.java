package com.moveapps.security.user.domains.services;

import org.springframework.security.authentication.AuthenticationManager;

import com.moveapps.security.user.domains.data.LoginResponse;
import com.moveapps.security.user.domains.data.TokenValidateResponse;
import com.moveapps.security.user.domains.data.UserDTO;
import com.moveapps.security.user.infraestructure.entities.UserEntity;

public interface IAuthService {
	UserEntity saveUser(UserEntity userEntity) ;

    LoginResponse login(UserDTO userDTO) ;

    TokenValidateResponse validateToken(String token) ;
}
