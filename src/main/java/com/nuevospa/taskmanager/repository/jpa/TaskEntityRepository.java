package com.nuevospa.taskmanager.repository.jpa;

import com.nuevospa.taskmanager.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, UUID> {
}
