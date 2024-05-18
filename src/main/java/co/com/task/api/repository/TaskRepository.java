package co.com.task.api.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import co.com.task.api.domain.Task;

public interface TaskRepository extends CrudRepository<Task, UUID> {
}
