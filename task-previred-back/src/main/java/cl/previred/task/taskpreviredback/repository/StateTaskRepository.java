package cl.previred.task.taskpreviredback.repository;

import cl.previred.task.taskpreviredback.domain.StateTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateTaskRepository extends JpaRepository<StateTask, Long> {
}
