package com.nuevospa.task.management.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevospa.task.management.data.entity.TaskDao;

public interface ITaskRepository extends JpaRepository<TaskDao, Long>{	
	

}
