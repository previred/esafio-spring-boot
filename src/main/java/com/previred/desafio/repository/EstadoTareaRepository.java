package com.previred.desafio.repository;

import com.previred.desafio.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * EstadoTareaRepository.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {
}
