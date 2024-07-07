package com.nuevo.spa.taskmanagement.repository;

import com.nuevo.spa.taskmanagement.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {
}
