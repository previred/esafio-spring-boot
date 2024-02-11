package com.previred.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.previred.challenge.dto.UsuariosDto;
import com.previred.challenge.model.Usuarios;
import com.previred.challenge.service.UsuariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios Controller", description = "Endpoints para Usuarios")
@RestController
@RequestMapping("/auth")
public class UsuariosController {
	
	@Autowired
	private UsuariosService userService;
	
	@Operation(summary = "Obtener Token de un usuario registrado", 
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(example = "{\"username\":\"user\",\"password\":\"pass\"}"))))
	@PostMapping("/login")
	public @ResponseBody UsuariosDto getUser(@RequestBody Usuarios login){
		UsuariosDto user = new UsuariosDto();
		try {
			user = userService.loadUserByUser(login);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Operation(summary = "Crear nuevo usuario", 
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
            		example = "{\"name\":\"Nombre\",\"lastname\":\"Apellido\",\"username\":\"username\",\"password\":\"pass\",\"mail\":\"mail@mail.com\",\"role\":{\"name\":\"ADMIN o GENERAL_USER\"}}"))))
	@PostMapping("/createuser")
	public  @ResponseBody String createNewUser(@RequestBody Usuarios login){
		try {
			login.setPassword(new BCryptPasswordEncoder().encode(login.getPassword()));
			userService.createNewUser(login);
			return "Nuevo usuario creado";
		} catch (Exception e) {
			e.printStackTrace();
			return "Existio un problema al crear nuevo usuario";
		}
	}
}
