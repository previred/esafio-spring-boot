package cl.nuevo.spa.taskmanager.service;

import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import cl.nuevo.spa.taskmanager.domain.exception.NotFoundTaskException;
import cl.nuevo.spa.taskmanager.mapper.TaskDtoToTaskEntityMapper;
import cl.nuevo.spa.taskmanager.mapper.TaskEntityToTaskDtoMapper;
import cl.nuevo.spa.taskmanager.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Task service. */
@Service
@RequiredArgsConstructor
public class TaskService {

  private static final String ERROR_CODE_GET_TASK_BY_ID = "0140401";
  private static final String ERROR_UPDATE_TASK = "0140402";
  private static final String ERROR_CODE_DELETE_BY_ID = "0140403";

  private final TaskRepository taskRepository;
  private final TaskEntityToTaskDtoMapper taskEntityToTaskDtoMapper;
  private final TaskDtoToTaskEntityMapper taskDtoToTaskEntityMapper;

  /**
   * Gets all tasks.
   *
   * @return the all tasks
   */
  public List<TaskDto> getAllTasks() {
    List<TaskEntity> taskEntities = taskRepository.findAll();
    return taskEntities.stream().map(taskEntityToTaskDtoMapper::map).collect(Collectors.toList());
  }

  /**
   * Gets task by id.
   *
   * @param taskId the task id
   * @return the task by id
   */
  public TaskDto getTaskById(Long taskId) {
    Optional<TaskEntity> taskOptional = taskRepository.findById(taskId);
    return taskOptional
        .map(taskEntityToTaskDtoMapper::map)
        .orElseThrow(
            () -> new NotFoundTaskException(String.valueOf(taskId), ERROR_CODE_GET_TASK_BY_ID));
  }

  /**
   * Create task task dto.
   *
   * @param taskDTO the task dto
   * @return the task dto
   */
  public TaskDto createTask(TaskDto taskDTO) {
    TaskEntity taskEntity = taskDtoToTaskEntityMapper.map(taskDTO);
    TaskEntity createdTaskEntity = taskRepository.save(taskEntity);
    return taskEntityToTaskDtoMapper.map(createdTaskEntity);
  }

  /**
   * Update task task dto.
   *
   * @param taskDTO the task dto
   * @return the task dto
   */
  public TaskDto updateTask(TaskDto taskDTO) {
    TaskEntity existingTaskEntity =
        taskRepository
            .findById(taskDTO.getId())
            .orElseThrow(
                () ->
                    new NotFoundTaskException(String.valueOf(taskDTO.getId()), ERROR_UPDATE_TASK));
    TaskEntity taskEntity = taskDtoToTaskEntityMapper.map(taskDTO, existingTaskEntity);
    TaskEntity updatedTaskEntity = taskRepository.save(taskEntity);
    return taskEntityToTaskDtoMapper.map(updatedTaskEntity);
  }

  /**
   * Delete task.
   *
   * @param taskId the task id
   */
  public void deleteTask(Long taskId) {
    if (!taskRepository.existsById(taskId)) {
      throw new NotFoundTaskException(String.valueOf(taskId), ERROR_CODE_DELETE_BY_ID);
    }
    taskRepository.deleteById(taskId);
  }
}
