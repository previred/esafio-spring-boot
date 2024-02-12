package org.openapitools.repository.jpa;

import org.openapitools.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskStatusEntityRepository extends JpaRepository<TaskStatusEntity, UUID> {
}
