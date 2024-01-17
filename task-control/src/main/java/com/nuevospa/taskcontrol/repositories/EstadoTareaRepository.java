package com.nuevospa.taskcontrol.repositories;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {

}
