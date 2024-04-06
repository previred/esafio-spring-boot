package com.platform.task.backend.service;

import java.util.List;

import com.platform.task.backend.entity.Usuario;
import com.platform.task.backend.entity.Task;

public interface UserService {

	Usuario create(Usuario usuario);

	Usuario findById(Long id);

	List<Usuario> getUser();

	void deleteTask(Long id);

}
