package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import cl.nuevo.spa.taskmanager.domain.entity.TaskStatusEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/** The type Task dto to task entity mapper. */
@Component
public class TaskDtoToTaskEntityMapper
    implements Mapper<TaskDto, TaskEntity>, BiMapper<TaskDto, TaskEntity, TaskEntity> {

  @Override
  public TaskEntity map(TaskDto taskDTO, TaskEntity taskEntity) {
    TaskEntity newTaskEntity = new TaskEntity();
    BeanUtils.copyProperties(taskEntity, newTaskEntity);
    BeanUtils.copyProperties(taskDTO, newTaskEntity);
    newTaskEntity.setStatus(new TaskStatusEntity(taskDTO.getStatus()));
    newTaskEntity.setId(taskDTO.getId());
    return newTaskEntity;
  }

  @Override
  public TaskEntity map(TaskDto taskDTO) {
    TaskEntity taskEntity = new TaskEntity();
    BeanUtils.copyProperties(taskDTO, taskEntity);
    taskEntity.setStatus(new TaskStatusEntity(taskDTO.getStatus()));
    return taskEntity;
  }
}
