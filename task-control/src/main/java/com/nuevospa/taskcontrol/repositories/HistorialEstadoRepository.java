package com.nuevospa.taskcontrol.repositories;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import com.nuevospa.taskcontrol.dtos.entities.HistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistorialEstadoRepository extends JpaRepository<HistorialEstado, Long> {

    Optional<HistorialEstado> findByEstadoTarea(EstadoTarea estadoTarea);
}
