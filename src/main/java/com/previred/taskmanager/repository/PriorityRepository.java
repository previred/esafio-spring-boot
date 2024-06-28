package com.previred.taskmanager.repository;

import com.previred.taskmanager.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findByName(String name);
}
