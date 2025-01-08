package co.api.gestiontareas.domain.model.task.repository;

import co.api.gestiontareas.domain.model.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskGateway {
    Task save(Task tacks);

    Optional<Task> getTask(Long id);

    boolean existById(Long id);

    List<Task> getTaskAll();
}
