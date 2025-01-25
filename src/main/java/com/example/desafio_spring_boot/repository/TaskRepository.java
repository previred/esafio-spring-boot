package com.example.desafio_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafio_spring_boot.domain.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
