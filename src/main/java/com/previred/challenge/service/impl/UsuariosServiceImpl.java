package com.previred.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.previred.challenge.dto.UsuariosDto;
import com.previred.challenge.model.Roles;
import com.previred.challenge.model.Usuarios;
import com.previred.challenge.repository.RolesRepository;
import com.previred.challenge.repository.UsuariosRepository;
import com.previred.challenge.service.UsuariosService;

import com.previred.challenge.jwt.JwtService;


@Service
public class UsuariosServiceImpl implements UsuariosService {
    
	@Autowired
	private UsuariosRepository userRepository;
	
	@Autowired
	private RolesRepository rolesRepository;
    
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
    public UsuariosDto loadUserByUser(Usuarios user) throws Exception{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		Usuarios userModel = userRepository.findByUsername(user.getUsername());		
    	UsuariosDto userDto = modelToDtoUser(userModel);
    	String token = jwtService.getToken(userModel);
    	userDto.setToken(token);
        return userDto;
    }
	
	private UsuariosDto modelToDtoUser(Usuarios modelUser) throws Exception{
		UsuariosDto user = new UsuariosDto();
		user.setName(modelUser.getName());
		user.setLastname(modelUser.getLastname());
		user.setMail(modelUser.getMail());
		user.setRole(modelUser.getRole().getName());
		user.setUsername(modelUser.getUsername());
		
		return user;
	}

	@Override
	public void createNewUser(Usuarios user) throws Exception {
		Roles rol = rolesRepository.findRolByName(user.getRole().getName());
		if(rol != null) {
			user.setRole(rol);
			userRepository.save(user);	
		}
			
	}
	
	
}
