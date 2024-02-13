package cl.nuevospa.taskmanagement.converter;

import cl.nuevospa.taskmanagement.dto.TaskRequestDTO;
import cl.nuevospa.taskmanagement.dto.TaskResponseDTO;
import cl.nuevospa.taskmanagement.entity.Task;
import cl.nuevospa.taskmanagement.entity.TaskState;
import cl.nuevospa.taskmanagement.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter {

    // Convertir de TaskRequestDTO a Task (Entidad)
    public Task convertToEntity(TaskRequestDTO taskDTO, User user, TaskState taskState) {
        return Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .user(user)
                .taskState(taskState)
                .build();
    }

    // Convertir de Task (Entidad) a TaskResponseDTO
    public TaskResponseDTO convertToDTO(Task task) {

        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .creationDate(task.getCreationDate())
                .state(task.getTaskState().getName())
                .user(task.getUser().getUsername())
                .build();
    }

    // Convertir listas de Task a TaskResponseDTO
    public List<TaskResponseDTO> convertToDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
