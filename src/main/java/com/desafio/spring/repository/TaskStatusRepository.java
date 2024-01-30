package com.desafio.spring.repository;

import com.desafio.spring.repository.dao.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    TaskStatus findByNameStatusTask(String name);
}
