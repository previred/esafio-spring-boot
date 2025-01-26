package cl.previred.task.taskpreviredback.service.impl;

import cl.previred.task.taskpreviredback.domain.Task;
import cl.previred.task.taskpreviredback.repository.TaskRepository;
import cl.previred.task.taskpreviredback.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
     private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> getTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> getTaskByUser(Long idGrade) {
        return List.of();
    }

    @Override
    public Optional<Task> checkTaskById(Long id) {
        return Optional.empty();
    }

    @Override
    public Task addNewTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task updateTask(Task task) {
        Task updateTask = taskRepository.findById(task.getId())
                .orElseThrow(() -> new IllegalStateException("task with id " + task.getId() + " does not exist"));
        updateTask.setStateTask(task.getStateTask());
        updateTask.setTaskDescription(task.getTaskDescription());
        updateTask.setTaskTitle(task.getTaskTitle());
        return updateTask;
    }
}
