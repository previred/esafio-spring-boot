package com.platform.task.backend.service;

import org.springframework.stereotype.Service;

import com.platform.task.backend.entity.Usuario;
import com.platform.task.backend.entity.Task;
import com.platform.task.backend.repository.UserRepository;
import com.platform.task.backend.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Usuario create(Usuario Usuario) {
		return userRepository.save(Usuario);
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> optionalUser = userRepository.findById(id);
		return optionalUser.get();
	}

	@Override
	public List<Usuario> getUser() {
		return userRepository.findAll();
	}

	@Override
	public void deleteTask(Long id) {
		userRepository.deleteById(id);
	}
}
