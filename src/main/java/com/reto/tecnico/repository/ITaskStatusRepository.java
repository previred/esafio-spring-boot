package com.reto.tecnico.repository;

import com.reto.tecnico.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskStatusRepository extends JpaRepository<TaskStatus, Long> {

}
