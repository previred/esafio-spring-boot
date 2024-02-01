package com.desafio.spring.desafiospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.spring.desafiospringboot.model.AuthRequest;
import com.desafio.spring.desafiospringboot.model.Usuario;
import com.desafio.spring.desafiospringboot.service.UsuarioService;
import com.desafio.spring.desafiospringboot.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil JwtUtil;
	
	@Operation(hidden = true)
	@PostMapping("/nuevo")
    public String addNewUser(@RequestBody Usuario userInfo) {
        return usuarioService.agregarUsuario(userInfo);
    }
	
	@Operation(summary = "Autenticación de Usuario", description = "Autenticación que nos devuelve el token de autorización para consultar los demas endpoints\n\n"
			+ "Puede usar estos valores en el RequestBody: userName: 'admin', password: 'admin'",
			tags = {"Servicios"})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
  							content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
  							content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return JwtUtil.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("Usuario Invalido!");
        }
    }
	
	@Operation(hidden = true)
	@GetMapping("/obtenerUsuarios")
	public ResponseEntity<List<Usuario>> obtenerUsuarios() {
		return usuarioService.obtenerUsuarios();
	}

}
