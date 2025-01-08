package co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.task;

import co.api.gestiontareas.domain.model.task.Task;
import co.api.gestiontareas.domain.model.task.repository.TaskGateway;
import co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.helper.AdapterOperations;
import co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.task.entities.TaskEntity;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class TaskGatewayAdapter extends AdapterOperations<Task, TaskEntity, Long, TaskDataRepository>
 implements TaskGateway
{

    @Autowired
    public TaskGatewayAdapter(TaskDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Task.class));
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return repository.findById(id).map(this::toEntity);
    }

    @Override
    public boolean existById(Long id) {
        return this.repository.existsById(id);
    }

    @Override
    public List<Task> getTaskAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
