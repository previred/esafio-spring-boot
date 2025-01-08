package co.api.gestiontareas.domain.service;

import co.api.gestiontareas.domain.model.task.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);

    Task getTask(Long id);

    List<Task> getTasks();

    void deleteTask(Long id) throws Exception;

    void updateTask(Task task) throws Exception;
}
