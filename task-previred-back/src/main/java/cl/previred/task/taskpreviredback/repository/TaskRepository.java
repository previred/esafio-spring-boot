package cl.previred.task.taskpreviredback.repository;

import cl.previred.task.taskpreviredback.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
