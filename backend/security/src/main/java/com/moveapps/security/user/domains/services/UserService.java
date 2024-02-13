package com.moveapps.security.user.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.moveapps.commons.api.applications.model.StatusDomain.DELETE;
import static com.moveapps.commons.api.domains.data.ResponseBaseStatusDomain.ERROR;
import static com.moveapps.commons.api.infraestructure.configs.Constants.UNEXPECTED_ERROR;
import static com.moveapps.security.user.infraestructure.confing.Constants.*;

import com.moveapps.commons.api.domains.exception.BaseException;
import com.moveapps.commons.api.domains.services.EndPointServiceImpl;
import com.moveapps.security.user.domains.data.LoginResponse;
import com.moveapps.security.user.domains.data.TokenValidateResponse;
import com.moveapps.security.user.domains.data.UserDTO;
import com.moveapps.security.user.infraestructure.entities.UserEntity;
import com.moveapps.security.user.infraestructure.repositories.UserRepository;

import lombok.SneakyThrows;
@Service
public class UserService  extends EndPointServiceImpl< UserDTO, UserEntity, String> implements IAuthService{
	@Autowired
	UserRepository repository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Override
	public JpaRepository<UserEntity, String> getDao() {
		return this.repository;
	}

	@Override
	public UserEntity statusChangeDelete(UserEntity entity) {
		entity.setStatus(DELETE);
		return entity;
	}

	@Override
	public String nameModule() {
		return MANAGEMENT_MODULE;
	}

	@Override
	public String className() {
		return USER_SERVICE;
	}

	@Override
	@SneakyThrows
	public UserEntity saveUser(UserEntity userEntity) {
		try {
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
	       	return this.save(userEntity);
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
		
	}

	@Override
	@SneakyThrows
	public LoginResponse login(UserDTO userDTO) {
		try {
			LoginResponse loginResponse = new LoginResponse();
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
	        loginResponse.setLoggedIn(false);
			if (authenticate.isAuthenticated()) {
				loginResponse.setLoggedIn(true);
				loginResponse.setToken(jwtService.generateToken(userDTO.getUsername()));
	        } 
	        return loginResponse;
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public TokenValidateResponse validateToken(String token) {
		try {
			TokenValidateResponse tokenValidateResponsee = new TokenValidateResponse();
			tokenValidateResponsee.setValid(true);
			return tokenValidateResponsee;
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

}