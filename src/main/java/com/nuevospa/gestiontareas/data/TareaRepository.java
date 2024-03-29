package com.nuevospa.gestiontareas.data;

import com.nuevospa.gestiontareas.model.tareas.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEstadoTareaNombre(String nombreEstado);

    List<Tarea> findByUsuarioId(Long usuarioId);

    Optional<Tarea> findById(Long id);
}
