package ar.com.challenge.desafio_spring_boot.repository;

import ar.com.challenge.desafio_spring_boot.entity.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskStateRepository  extends JpaRepository<TaskState, Integer> {

    Optional<TaskState> findByStatus(String status);
}
