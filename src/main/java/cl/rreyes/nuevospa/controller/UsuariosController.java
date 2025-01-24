package cl.rreyes.nuevospa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.rreyes.nuevospa.model.Usuario;
import cl.rreyes.nuevospa.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/all")
	public List<Usuario> listar() {
		return service.getAllUsuarios();
	}

	@PostMapping
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
		usuario.setId(null);
		Usuario nuevoUsuario = service.registerUser(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	}

	@PutMapping("/{id}")
	public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuarioActualizado = service.updateUsuario(id, usuario);		
		return usuarioActualizado;
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		service.deleteUsuario(id);
	}

}
