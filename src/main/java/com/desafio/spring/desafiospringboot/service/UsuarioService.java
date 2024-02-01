package com.desafio.spring.desafiospringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.spring.desafiospringboot.dao.UsuarioRepository;
import com.desafio.spring.desafiospringboot.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<List<Usuario>> obtenerUsuarios() {
		try {
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			usuarioRepository.findAll().forEach(listaUsuarios::add);
			
			if(listaUsuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			listaUsuarios.forEach(usuario -> usuario.setPassword("******"));//solo para que no se muestren las pass
			return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public String agregarUsuario(Usuario userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		usuarioRepository.save(userInfo);
        return "Usuario agregado!!";
	}

}
