package com.nuevospa.apirest.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nuevospa.apirest.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
	
	//lista todos las tareas status
    List<Task> findByStatusId(Long statusId);

	
}