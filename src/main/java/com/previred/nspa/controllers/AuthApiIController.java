package com.previred.nspa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.previred.nspa.api.AuthApi;
import com.previred.nspa.model.AuthPost200ResponseDTO;
import com.previred.nspa.model.UsuariosDTO;
import com.previred.nspa.service.JwtService;
import com.previred.nspa.service.UsuarioService;

@RestController
@RequestMapping("/autorizacionusuario")
public class AuthApiIController implements AuthApi {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private JwtService jwtService;
	
    private final NativeWebRequest request;

    public AuthApiIController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    
    @Override
    public ResponseEntity<AuthPost200ResponseDTO> authPost(UsuariosDTO usuariosDTO) {
        if (usuarioService.validarUsuario(usuariosDTO)) {
            String token = jwtService.generateToken(usuariosDTO);
            AuthPost200ResponseDTO responseDTO = new AuthPost200ResponseDTO();
            responseDTO.setToken(token);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}