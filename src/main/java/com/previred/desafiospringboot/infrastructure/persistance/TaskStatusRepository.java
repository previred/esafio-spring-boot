package com.previred.desafiospringboot.infrastructure.persistance;

import com.previred.desafiospringboot.domain.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus,Integer> {
}
