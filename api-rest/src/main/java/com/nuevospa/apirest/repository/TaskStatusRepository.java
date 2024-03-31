package com.nuevospa.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.apirest.model.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus,Long>{

}
