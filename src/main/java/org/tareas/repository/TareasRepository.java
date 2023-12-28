package org.tareas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.tareas.model.Tarea;

public interface TareasRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findAll();
    
    //List<Tareas> findByUsuarios(Integer usuarios_id);

    List<Tarea> findByEstadoContaining(Integer estado);

    List<Tarea> findByEstado(Integer estado);
}