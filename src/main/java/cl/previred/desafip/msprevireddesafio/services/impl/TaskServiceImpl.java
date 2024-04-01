package cl.previred.desafip.msprevireddesafio.services.impl;

import cl.previred.desafip.msprevireddesafio.entities.Task;
import cl.previred.desafip.msprevireddesafio.repositories.TaskRepository;
import cl.previred.desafip.msprevireddesafio.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            return taskOptional.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if (taskOptional.isPresent()) {
            taskOptional.get().setDescription(task.getDescription());
            taskOptional.get().setUser(task.getUser());
            taskOptional.get().setName(task.getName());
            taskOptional.get().setState(task.getState());
            return taskRepository.save(taskOptional.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

    public boolean deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
        return true;
    }
}
