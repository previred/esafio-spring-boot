package com.nuevospa.gestion_tareas.repository;

import com.nuevospa.gestion_tareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUsuarioId(Long usuarioId);
}
