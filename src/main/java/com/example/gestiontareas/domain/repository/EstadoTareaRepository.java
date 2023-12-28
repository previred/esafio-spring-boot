package com.example.gestiontareas.domain.repository;

import com.example.gestiontareas.domain.model.EstadoTarea;
import org.springframework.data.repository.CrudRepository;

public interface EstadoTareaRepository extends CrudRepository<EstadoTarea, Long> {
}
