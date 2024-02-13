package cl.nuevospa.taskmanagement.service;

import cl.nuevospa.taskmanagement.dto.TaskRequestDTO;
import cl.nuevospa.taskmanagement.dto.TaskResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO taskDTO) throws Exception;
    TaskResponseDTO updateTask(UUID taskId, TaskRequestDTO taskDTO);
    void deleteTask(UUID taskId);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(UUID taskId);
}
