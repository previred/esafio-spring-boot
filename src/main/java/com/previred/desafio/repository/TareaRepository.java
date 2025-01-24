package com.previred.desafio.repository;

import com.previred.desafio.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TareaRepository.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
