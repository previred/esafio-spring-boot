package com.previred.desafio_spring_boot.repository;

import com.previred.desafio_spring_boot.Domain.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {
}
