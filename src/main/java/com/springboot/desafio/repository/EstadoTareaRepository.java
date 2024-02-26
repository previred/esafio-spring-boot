package com.springboot.desafio.repository;

import com.springboot.desafio.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {

    EstadoTarea getEstadoTareaByNombre(String nombre);
}
