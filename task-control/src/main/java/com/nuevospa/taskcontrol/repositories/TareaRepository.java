package com.nuevospa.taskcontrol.repositories;

import com.nuevospa.taskcontrol.dtos.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository <Tarea, Long> {
}
