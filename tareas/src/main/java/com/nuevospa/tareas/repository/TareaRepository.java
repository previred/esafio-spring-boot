package com.nuevospa.tareas.repository;

import com.nuevospa.tareas.entity.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
}
