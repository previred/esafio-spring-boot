package smarroquin.desafiospringboot.services;

import smarroquin.desafiospringboot.controllers.LoginResponse;
import smarroquin.desafiospringboot.entities.DTO.UserDTO;

public interface LoginService {
	
	public LoginResponse login(UserDTO userDTO);

}