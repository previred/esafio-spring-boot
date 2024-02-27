package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	

    @Autowired
    private UsuarioRepository userRepository;

	public Optional<Usuario> existsByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<Usuario> getAllUsuarios() {
		return userRepository.findAll();
	}

	public Optional<Usuario> getUsuarioById(Long id) {
		return userRepository.findById(id);
	}

	public boolean deleteUsuario(Long id) {
	    try {
	        userRepository.deleteById(id);
	        return true; // Se eliminó correctamente
	    } catch (Exception e) {
	        return false; // Hubo un error al eliminar
	    }
	}


	public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
	    Optional<Usuario> user = userRepository.findById(id);
	    if (user.isPresent()) {
	        Usuario userToUpdate = user.get();
	        userToUpdate.setCountry(usuario.getCountry());
	        userToUpdate.setFirstname(usuario.getFirstname());
	        userToUpdate.setLastname(usuario.getLastname());
	        userToUpdate.setPassword(usuario.getPassword());
	        userToUpdate.setRole(usuario.getRole());
	        userToUpdate.setUsername(usuario.getUsername());

	        return Optional.of(userRepository.save(userToUpdate));
	    } else {
	        return Optional.empty();
	    }
	}
}
