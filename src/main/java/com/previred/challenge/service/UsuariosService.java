package com.previred.challenge.service;


import com.previred.challenge.dto.UsuariosDto;
import com.previred.challenge.model.Usuarios;

public interface UsuariosService {
	
	UsuariosDto loadUserByUser(Usuarios user) throws Exception;
	
	void createNewUser(Usuarios user) throws Exception;
}
