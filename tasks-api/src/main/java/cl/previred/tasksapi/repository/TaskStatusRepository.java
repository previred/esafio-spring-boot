package cl.previred.tasksapi.repository;

import cl.previred.tasksapi.model.TaskStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository  extends JpaRepository<TaskStatusModel, Integer> {
}
