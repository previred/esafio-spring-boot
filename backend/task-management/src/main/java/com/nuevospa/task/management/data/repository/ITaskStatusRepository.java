package com.nuevospa.task.management.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevospa.task.management.data.entity.TaskStatusDao;

public interface ITaskStatusRepository extends JpaRepository<TaskStatusDao, Long>{	
	
	Optional<TaskStatusDao> findByStatus(String status);
}
