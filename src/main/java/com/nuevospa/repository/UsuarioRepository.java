package com.nuevospa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevospa.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity findByNombreAndPassword(String nombre, String password);
	
	UsuarioEntity findByNombre(String nombre);
	
	
}