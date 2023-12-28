package com.nuevospa.task.management.services.user.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nuevospa.task.management.configuration.GenerateJwtToken;
import com.nuevospa.task.management.data.entity.UserDao;
import com.nuevospa.task.management.data.repository.IUserRepository;
import com.nuevospa.task.management.dto.user.CreateUserDto;
import com.nuevospa.task.management.dto.user.LoginUser;
import com.nuevospa.task.management.dto.user.ResponseCreateUserDto;
import com.nuevospa.task.management.dto.user.ResponseLoginUserDto;
import com.nuevospa.task.management.exception.BadPasswordUserException;
import com.nuevospa.task.management.exception.TokenException;
import com.nuevospa.task.management.exception.UserException;
import com.nuevospa.task.management.exception.UserExistsException;
import com.nuevospa.task.management.exception.UserNotFoundException;
import com.nuevospa.task.management.services.user.IUserService;
import com.nuevospa.task.management.util.CodesErrorsConstants;
import com.nuevospa.task.management.util.MessageErrorsConstants;


@Service
public class UserServiceImpl implements IUserService{
	

    
    @Autowired
    private IUserRepository repositoryUser;
    
    
    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;
 
	@Override
	public ResponseCreateUserDto createUser(CreateUserDto reqCreateUsuario, String token) {
		
		ResponseCreateUserDto response = new ResponseCreateUserDto();
		
		
		String emailToken = GenerateJwtToken.getEmailToken(token);
		
		
		if(!repositoryUser.existsByEmail(emailToken) || !GenerateJwtToken.validateToken(token, emailToken)) {
			throw new TokenException(MessageErrorsConstants.TOKEN_EXCEPTION_MESSAGE, CodesErrorsConstants.TOKEN_EXCEPTION_MESSAGE_CODE);
		}
		

		repositoryUser.findByEmail(reqCreateUsuario.getEmail())
	    .ifPresent(user -> {
	        throw new UserExistsException(MessageErrorsConstants.USER_EXIST_MESSAGE_ERROR, CodesErrorsConstants.USER_EXIST_MESSAGE_ERROR_CODE);
	    });
		
		
		try {
			
			UserDao saveUsr = repositoryUser.save(mapperUserDao(reqCreateUsuario));
			response = mapperResponse(saveUsr);
			
		} catch (Exception e) {
			throw new UserException(MessageErrorsConstants.USER_ERROR_EXCEPTION_MESSAGE + " " + e.getMessage(), CodesErrorsConstants.USER_ERROR_EXCEPTION_MESSAGE_CODE);
		}
		
		return response;
	}
	
	
	private UserDao mapperUserDao(CreateUserDto createUser) {
		
	
		UserDao userDao = new UserDao();
		
		userDao.setName(createUser.getName());	
		userDao.setPassword(bCryptEncoder.encode(createUser.getPassword()));		
		userDao.setEmail(createUser.getEmail());				
		userDao.setCreated(LocalDateTime.now());		
		userDao.setLastLogin(LocalDateTime.now());	
		userDao.setStatus(true);
		
		
		return userDao;
		
	}
	
	
	private ResponseCreateUserDto mapperResponse(UserDao saveUsr) {
		
		ResponseCreateUserDto response = ResponseCreateUserDto.builder()
				.name(saveUsr.getName())
				.email(saveUsr.getEmail())
				.created(saveUsr.getCreated())
				.lastLogin(saveUsr.getLastLogin())
				.isActive(saveUsr.isStatus())
				.build();
		
		
		return response;
			
	}


	@Override
	public ResponseLoginUserDto loginUser(LoginUser user) {
		
		ResponseLoginUserDto response = new ResponseLoginUserDto();
	
		Optional<UserDao> validUser = repositoryUser.findByEmail(user.getEmail());
				
		if(!validUser.isPresent()) {
			throw new UserNotFoundException(MessageErrorsConstants.USER_NOT_FOUND_MESSAGE, CodesErrorsConstants.USER_NOT_FOUND_MESSAGE_CODE);
		}

		UserDao userDao = validUser.get();
		
		
		if(!bCryptEncoder.matches(user.getPassword(), userDao.getPassword())) {
			
			throw new BadPasswordUserException(MessageErrorsConstants.BAD_PASSWORD_EXCEPTION, CodesErrorsConstants.BAD_PASSWORD_EXCEPTION_CODE);
			
		}
		
		try {

			userDao.setLastLogin(LocalDateTime.now());
			
			UserDao userSave = repositoryUser.save(userDao);
			
			response = responseLoginMapper(userSave, GenerateJwtToken.generateToken(userDao.getEmail()));
			
			
		} catch (Exception e) {
			throw new UserException(MessageErrorsConstants.USER_ERROR_EXCEPTION_MESSAGE + " " +  e.getMessage(), CodesErrorsConstants.USER_ERROR_EXCEPTION_MESSAGE_CODE);
		}
		
		
		return response;
	}
	
	
	private ResponseLoginUserDto responseLoginMapper(UserDao userSave, String token) {

		ResponseLoginUserDto response = new ResponseLoginUserDto();

		response.setCreated(userSave.getCreated());
		response.setLastLogin(userSave.getLastLogin());
		response.setToken(token);
		response.setActive(userSave.isStatus());
		response.setName(userSave.getName());
		response.setEmail(userSave.getEmail());

		return response;

	}
	
	
	
	
}
