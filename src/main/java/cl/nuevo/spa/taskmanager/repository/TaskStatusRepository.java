package cl.nuevo.spa.taskmanager.repository;

import cl.nuevo.spa.taskmanager.domain.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Integer> {}
