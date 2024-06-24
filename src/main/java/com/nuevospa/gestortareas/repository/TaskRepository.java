package com.nuevospa.gestortareas.repository;

import com.nuevospa.gestortareas.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserUsername(String username);
}

