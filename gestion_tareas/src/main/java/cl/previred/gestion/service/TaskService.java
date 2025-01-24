package cl.previred.gestion.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.previred.gestion.model.Task;
import cl.previred.gestion.model.TaskStatus;
import cl.previred.gestion.repository.TaskRepository;
import cl.previred.gestion.repository.TaskStatusRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;

    public TaskService(TaskRepository taskRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public TaskStatus findStatusByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("El estado de la tarea no puede ser null");
        }
        return taskStatusRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Estado de tarea invalido: " + name));
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public boolean deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            return false; 
        }
        taskRepository.delete(taskOptional.get());
        return true;
    }
    
}

