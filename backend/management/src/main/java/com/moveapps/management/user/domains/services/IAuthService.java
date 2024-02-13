package com.moveapps.management.user.domains.services;

import org.springframework.security.authentication.AuthenticationManager;

import com.moveapps.management.user.domains.data.LoginResponse;
import com.moveapps.management.user.domains.data.TokenValidateResponse;
import com.moveapps.management.user.domains.data.UserDTO;
import com.moveapps.management.user.infraestructure.entities.UserEntity;

public interface IAuthService {
	UserEntity saveUser(UserEntity userEntity) ;

    LoginResponse login(UserDTO userDTO) ;

    TokenValidateResponse validateToken(String token) ;
}
