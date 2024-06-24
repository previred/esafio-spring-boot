package com.nuevospa.gestortareas.service;

import com.nuevospa.gestortareas.dto.TaskResponseDTO;
import com.nuevospa.gestortareas.dto.UpdateTaskDTO;
import com.nuevospa.gestortareas.entity.Task;
import com.nuevospa.gestortareas.entity.TaskStatus;
import com.nuevospa.gestortareas.repository.TaskRepository;
import com.nuevospa.gestortareas.repository.TaskStatusRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;

    public TaskService(TaskRepository taskRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> listaTareas = taskRepository.findAll();
        return listaTareas.stream().map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getInformation(),
                task.getUser().getUsername(),
                task.getStatus().getName()
        )).collect(Collectors.toList());
    }

    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getInformation(),
                        task.getUser().getUsername(),
                        task.getStatus().getName()
                ));
    }

    public Task createTask(Task task) {
    	if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("El título de la tarea es obligatorio");
        }
        
        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tarea es obligatoria");
        }
    	
        // Establecer el estado por defecto "PENDIENTE" si no se ha establecido
        TaskStatus pendingStatus = taskStatusRepository.findByName("PENDIENTE")
                .orElseThrow(() -> new RuntimeException("Estado 'PENDIENTE' no encontrado"));
        task.setStatus(pendingStatus);    	
    	
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, @Valid UpdateTaskDTO updateTaskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        // Validar información no nula o vacía
        if (updateTaskDTO.getInformation() != null) {
            task.setInformation(updateTaskDTO.getInformation());
        } else {
            throw new IllegalArgumentException("El campo 'information' no puede estar vacío");
        }
        
        // Validar y establecer el estado de la tarea si se proporciona
        if (updateTaskDTO.getStatus() != null) {
            validateAndSetTaskStatus(updateTaskDTO.getStatus(), task);
        }

        return taskRepository.save(task);
    }

    private void validateAndSetTaskStatus(@NotBlank String statusName, Task task) {
        Optional<TaskStatus> optionalTaskStatus = taskStatusRepository.findByName(statusName.toUpperCase());
        if (optionalTaskStatus.isPresent()) {
            task.setStatus(optionalTaskStatus.get());
        } else {
            throw new IllegalArgumentException("Estado de tarea inválido: " + statusName);
        }
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
