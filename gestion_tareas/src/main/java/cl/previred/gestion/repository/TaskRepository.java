package cl.previred.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.previred.gestion.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
