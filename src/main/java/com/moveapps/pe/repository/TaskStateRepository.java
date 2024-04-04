package com.moveapps.pe.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moveapps.pe.entities.Task;
import com.moveapps.pe.entities.TaskState;
 

@Repository
public interface TaskStateRepository extends JpaRepository<TaskState, Long> {

}