package com.nuevospa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevospa.entity.EstadoTareaEntity;

public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity, Long> {

	EstadoTareaEntity findByNombre(String nombre);
	
	List<EstadoTareaEntity> findAll();
	
}
