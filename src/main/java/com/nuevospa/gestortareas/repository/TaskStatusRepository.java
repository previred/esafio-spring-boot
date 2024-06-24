package com.nuevospa.gestortareas.repository;

import com.nuevospa.gestortareas.entity.TaskStatus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    // Método para encontrar un TaskStatus por nombre
    Optional<TaskStatus> findByName(String name);
    
    // Método para verificar si un TaskStatus existe por nombre
    boolean existsByName(String name);
    
    // Método para eliminar un TaskStatus por nombre
    void deleteByName(String name);

}