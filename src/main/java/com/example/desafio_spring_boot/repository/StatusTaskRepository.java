package com.example.desafio_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafio_spring_boot.domain.status_task.StatusTask;

@Repository
public interface StatusTaskRepository extends JpaRepository<StatusTask, Long> {
}
