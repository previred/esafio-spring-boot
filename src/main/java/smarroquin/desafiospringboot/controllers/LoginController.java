package smarroquin.desafiospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smarroquin.desafiospringboot.entities.DTO.UserDTO;
import smarroquin.desafiospringboot.services.LoginService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	private LoginService userService;
	
	@PostMapping
	public ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userService.login(userDTO));
	}
}