package com.example.demo.model.repository;

import com.example.demo.model.entity.Task;
import com.example.demo.model.entity.TaskStatus;
import com.example.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

    Optional<TaskStatus> findOneByStatusCode(String code);
}
