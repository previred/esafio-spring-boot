package cl.nuevo.spa.taskmanager.repository;

import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** The interface Task repository. */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {}
