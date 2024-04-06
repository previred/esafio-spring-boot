package com.platform.task.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platform.task.backend.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

}
