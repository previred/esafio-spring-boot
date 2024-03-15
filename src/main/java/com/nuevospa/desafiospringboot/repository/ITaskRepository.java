package com.nuevospa.desafiospringboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.desafiospringboot.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {
    
}
