package cl.previred.gestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.previred.gestion.model.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    Optional<TaskStatus> findByName(String name);
}
