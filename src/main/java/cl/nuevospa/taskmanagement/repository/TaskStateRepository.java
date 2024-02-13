package cl.nuevospa.taskmanagement.repository;

import cl.nuevospa.taskmanagement.entity.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskStateRepository extends JpaRepository<TaskState, Long> {
    Optional<TaskState> findByName(String name);
}
