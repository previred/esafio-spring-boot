package com.nuevospa.app.repositories;

import com.nuevospa.app.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    TaskStatus findByName(String name);
}
