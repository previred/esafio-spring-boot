package com.test.previred.infrastructure.adapter.h2.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDataRepository extends JpaRepository<TaskEntity, Long> {
}
