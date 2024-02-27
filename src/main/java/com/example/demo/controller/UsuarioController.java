package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);


	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    UserDetailsService userDetailsService;

	// Endpoint para consultar todos los usuarios
	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsuarios();
	}

	// Endpoint para consultar un solo usuario por su ID
	@GetMapping("{id}") // Aquí especificamos la ruta /usuarios/get/{id} para el verbo GET
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
		return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Endpoint para eliminar un usuario por su ID
	@DeleteMapping("/{id}") // Aquí especificamos la ruta /usuarios/delete/{id} para el verbo DELETE
	public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
		if (usuarioService.deleteUsuario(id)) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint para actualizar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		try {
			// Verificar la disponibilidad del UserDetailsService
			if (userDetailsService != null) {
				// Procesar la actualización del usuario aquí
				// Llamada al servicio de usuario para actualizar el usuario
				Optional<Usuario> updatedUsuario = usuarioService.updateUsuario(id, usuario);
				if (updatedUsuario.isPresent()) {
					return ResponseEntity.ok("Usuario actualizado correctamente");
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
				}
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"UserDetailsService no está disponible");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error al actualizar usuario: " + e.getMessage(), e);
		}
	}

}
