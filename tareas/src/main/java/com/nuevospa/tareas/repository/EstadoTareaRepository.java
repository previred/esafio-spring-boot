package com.nuevospa.tareas.repository;

import com.nuevospa.tareas.entity.EstadoTareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity, Long> {
}
