package com.nuevospa.desafiospringboot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nuevospa.desafiospringboot.converter.Converter;
import com.nuevospa.desafiospringboot.dto.UserDTO;
import com.nuevospa.desafiospringboot.model.User;
import com.nuevospa.desafiospringboot.repository.IUserRepository;
import com.nuevospa.desafiospringboot.service.IUserService;
import com.nuevospa.desafiospringboot.validations.UserValidator;

@Service
public class UserServiceImpl implements IUserService {
 
    @Autowired
    private PasswordEncoder bcrypt;
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private UserValidator userValidator;

    @Override
    public UserDTO save(User user) {  	
    	userValidator.validateUser(user);
        user.setPassword(bcrypt.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return Converter.getMapper().map(savedUser, UserDTO.class);
    }

}
