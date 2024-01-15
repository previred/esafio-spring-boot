package com.challenge.spa.adapter.out.persistence.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTaskRepository extends JpaRepository<TaskEntity, String> {
}
