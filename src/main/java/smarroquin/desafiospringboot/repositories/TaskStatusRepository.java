package smarroquin.desafiospringboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smarroquin.desafiospringboot.entities.TaskStatus;
import smarroquin.desafiospringboot.entities.TaskStatus.Status;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
	
	public Optional<TaskStatus> findByStatus(Status status);
	
}