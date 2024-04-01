package cl.previred.desafip.msprevireddesafio.repositories;

import cl.previred.desafip.msprevireddesafio.entities.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStateRepository extends JpaRepository<TaskState, Long> {
}
