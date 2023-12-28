package com.example.gestiontareas.domain.repository;

import com.example.gestiontareas.domain.model.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends CrudRepository<Tarea, Long> {
}
