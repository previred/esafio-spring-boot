package cl.previred.desafip.msprevireddesafio.repositories;

import cl.previred.desafip.msprevireddesafio.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
