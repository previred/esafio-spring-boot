package cl.previred.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.previred.taskmanager.entity.StatusTask;

public interface StatusTaskRepository extends JpaRepository<StatusTask, Long> {

}
