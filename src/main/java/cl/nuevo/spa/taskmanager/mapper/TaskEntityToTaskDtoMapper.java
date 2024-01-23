package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.common.TaskStatus;
import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/** The type Task entity to task dto mapper. */
@Component
public class TaskEntityToTaskDtoMapper implements Mapper<TaskEntity, TaskDto> {

  @Override
  public TaskDto map(TaskEntity taskEntity) {
    TaskDto taskDTO = new TaskDto();
    BeanUtils.copyProperties(taskEntity, taskDTO);
    taskDTO.setStatus(TaskStatus.valueOf(taskEntity.getStatus().getName()));
    return taskDTO;
  }
}
