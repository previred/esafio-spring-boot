package cl.previred.challenge.service;

import cl.previred.challenge.exception.TaskStatusNotFoundException;
import cl.previred.challenge.exception.UserNotFoundException;
import cl.previred.challenge.model.Task;
import cl.previred.challenge.repository.TaskRepository;
import cl.previred.challenge.repository.TaskStatusRepository;
import cl.previred.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  private final UserRepository userRepository;

  private final TaskStatusRepository taskStatusRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskStatusRepository taskStatusRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
      this.taskStatusRepository = taskStatusRepository;
  }


  public Task saveTask(Task task) {
    taskStatusRepository
      .findById(task.getStatus().getId())
      .orElseThrow(() -> new TaskStatusNotFoundException("Estado indicado no existe"));

    userRepository
      .findById(task.getAssignedUser().getId())
      .orElseThrow(() -> new UserNotFoundException("Usuario indicado no existe"));

    return taskRepository.save(task);
  }


  public Optional<Task> findTaskById(Long id) {
    return taskRepository.findById(id);
  }


  public List<Task> findAllTasks() {
    return taskRepository.findAll();
  }


  public Optional<Task> updateTask(Long id, Task taskDetails) {
    Optional<Task> taskFound = taskRepository.findById(id);

    if (taskFound.isEmpty()) return Optional.empty();

    taskFound.get().setTitle(taskDetails.getTitle());
    taskFound.get().setDescription(taskDetails.getDescription());

    if (taskDetails.getStatus() != null) {
      taskStatusRepository
        .findById(taskDetails.getStatus().getId())
        .orElseThrow(() -> new TaskStatusNotFoundException("Estado indicado no existe"));

      taskFound.get().setStatus(taskDetails.getStatus());
    }

    if (taskDetails.getAssignedUser() != null) {
      userRepository
        .findById(taskDetails.getAssignedUser().getId())
        .orElseThrow(() -> new UserNotFoundException("Usuario indicado no existe"));

      taskFound.get().setAssignedUser(taskDetails.getAssignedUser());
    }

    taskRepository.save(taskFound.get());

    return taskFound;
  }


  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }
}
