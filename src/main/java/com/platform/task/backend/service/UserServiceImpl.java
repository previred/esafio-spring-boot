package com.platform.task.backend.service;

import org.springframework.stereotype.Service;

import com.platform.task.backend.entity.Usuario;
import com.platform.task.backend.entity.Task;
import com.platform.task.backend.repository.UsuarioRepository;
import com.platform.task.backend.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UsuarioRepository usuarioRepository;

	public UserServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario create(Usuario Usuario) {
		return usuarioRepository.save(Usuario);
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> optionalUser = usuarioRepository.findById(id);
		return optionalUser.get();
	}

	@Override
	public List<Usuario> getUser() {
		return usuarioRepository.findAll();
	}

	@Override
	public void deleteTask(Long id) {
		usuarioRepository.deleteById(id);
	}
}
