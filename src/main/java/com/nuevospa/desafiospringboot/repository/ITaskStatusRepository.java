package com.nuevospa.desafiospringboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.desafiospringboot.model.TaskStatus;

@Repository
public interface ITaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
    
}
