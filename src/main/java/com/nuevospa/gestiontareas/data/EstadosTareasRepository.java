package com.nuevospa.gestiontareas.data;

import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadosTareasRepository extends JpaRepository<EstadoTarea, Long> {
    Optional<EstadoTarea> findByNombre(String nombre);
    Optional<EstadoTarea> findById(Long id);
}
