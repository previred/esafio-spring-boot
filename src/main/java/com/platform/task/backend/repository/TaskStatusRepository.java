package com.platform.task.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.task.backend.entity.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

}
