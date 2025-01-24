package cl.rreyes.nuevospa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.rreyes.nuevospa.model.Usuario;
import cl.rreyes.nuevospa.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Obtener todos los usuarios
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario registerUser(Usuario usuario) {
		// Encriptar la contraseña antes de guardarla
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	// Actualizar tarea existente
	public Usuario updateUsuario(long id, Usuario updatedUsuario) {
		Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
		if (existingUsuario.isPresent()) {
			Usuario usuario = existingUsuario.get();
			usuario.setUsername(updatedUsuario.getUsername());
			usuario.setPassword(passwordEncoder.encode(updatedUsuario.getPassword()));

			return usuarioRepository.save(usuario);
		}
		return null;
	}

	// Obtener tarea por id
	public Optional<Usuario> getUsuarioById(long id) {
		return usuarioRepository.findById(id);
	}

	
	// Eliminar usuario por id
	public void deleteUsuario(long id) {
		usuarioRepository.deleteById(id);
	}
}
