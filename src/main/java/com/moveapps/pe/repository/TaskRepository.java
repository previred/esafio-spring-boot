package com.moveapps.pe.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moveapps.pe.entities.Task;
 

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}