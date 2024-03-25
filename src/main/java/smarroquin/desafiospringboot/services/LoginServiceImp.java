package smarroquin.desafiospringboot.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smarroquin.desafiospringboot.authentication.TokenConfiguration;
import smarroquin.desafiospringboot.controllers.LoginResponse;
import smarroquin.desafiospringboot.entities.User;
import smarroquin.desafiospringboot.entities.DTO.UserDTO;
import smarroquin.desafiospringboot.repositories.UserRepository;

@Service
public class LoginServiceImp implements LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	public LoginResponse login(UserDTO userDTO) {
		Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());
		
		if (optionalUser.isEmpty())
			return new LoginResponse(null, false, String.format("User %s not found", userDTO.getUsername()));
		
		if (!optionalUser.get().getPassword().equals(userDTO.getPassword()))
			return new LoginResponse(null, false, "Password doesn't match");
		
		return new LoginResponse(generateToken(optionalUser.get().getUsername()), true, null);
		
	}
	
	private String generateToken(String user) {
		return TokenConfiguration.generateToken(user);
	}
}