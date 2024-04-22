package com.company.persistence.task.status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, UUID> {
}
