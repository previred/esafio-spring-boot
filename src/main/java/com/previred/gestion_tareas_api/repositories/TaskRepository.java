package com.previred.gestion_tareas_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.gestion_tareas_api.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
