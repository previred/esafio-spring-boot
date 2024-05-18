package co.com.task.api.service;

import java.util.List;
import java.util.UUID;

import co.com.task.api.dto.TaskDTO;

public interface TaskService {

    public TaskDTO getById(UUID idTask);

    public List<TaskDTO> getAll();

    public TaskDTO save(TaskDTO task);

    public TaskDTO update(TaskDTO task);

    public void delete(UUID idTask);

}
