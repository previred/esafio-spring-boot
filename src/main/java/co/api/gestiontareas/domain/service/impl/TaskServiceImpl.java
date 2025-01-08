package co.api.gestiontareas.domain.service.impl;

import co.api.gestiontareas.domain.model.task.Task;
import co.api.gestiontareas.domain.model.task.repository.TaskGateway;
import co.api.gestiontareas.domain.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskGateway taskGateway;

    @Override
    public Task saveTask(Task task) {
        task.setStatus(2L);
        return taskGateway.save(task);
    }

    @Override
    public Task getTask(Long id) {
        Optional<Task> task = taskGateway.getTask(id);
        return task.orElse(null);
    }

    @Override
    public List<Task> getTasks() {
        return taskGateway.getTaskAll();
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        Optional<Task> taskOptional = taskGateway.getTask(id);
        if (taskOptional.isEmpty()) {
            throw new Exception("Tarea no encontrada");
        }
        Task task = taskOptional.get();
        task.setStatus(1L);
        taskGateway.save(task);
    }

    @Override
    public void updateTask(Task task) throws Exception {
        Optional<Task> taskOptional = taskGateway.getTask(task.getId());

        if (taskOptional.isEmpty()) {
            throw new Exception("No existe la tarea con el id " + task.getId());
        }

        Task taskToUpdate = taskOptional.get();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());

        taskGateway.save(taskToUpdate);
    }
}
