package cl.previred.challenge.service;

import cl.previred.challenge.model.TaskStatus;
import cl.previred.challenge.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusService {

  private final TaskStatusRepository taskStatusRepository;

  public TaskStatusService(TaskStatusRepository taskStatusRepository) {
    this.taskStatusRepository = taskStatusRepository;
  }

  public TaskStatus saveTaskStatus(TaskStatus taskStatus) {
    return taskStatusRepository.save(taskStatus);
  }

  public Optional<TaskStatus> findById(Long id) {
    return taskStatusRepository.findById(id);
  }

  public List<TaskStatus> findAll() {
    return taskStatusRepository.findAll();
  }

  public void deleteById(Long id) {
    taskStatusRepository.deleteById(id);
  }

  public Optional<TaskStatus> updateTaskStatus(Long id, TaskStatus taskStatus) {
    Optional<TaskStatus> taskStatusFound = taskStatusRepository.findById(id);

    if (taskStatusFound.isEmpty()) return Optional.empty();

    taskStatusFound.get().setName(taskStatus.getName());
    taskStatusRepository.save(taskStatusFound.get());

    return taskStatusFound;
  }

}
