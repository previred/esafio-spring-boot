package dev.rhc.apiuser.service;

import dev.rhc.apiuser.exception.UserNotFoundException;
import dev.rhc.apiuser.model.Task;
import dev.rhc.apiuser.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {
    private TaskRepository taskRepository;



    public Task findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        Task task = findById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setTaskState(updatedTask.getTaskState());
        task.setModified(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
