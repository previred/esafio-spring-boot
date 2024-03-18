package com.nuevospa.taskmanager.domain.repository;

import com.nuevospa.taskmanager.domain.model.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository<Tareas, Long> {

}
