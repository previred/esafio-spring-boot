package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.common.TaskStatus;
import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import cl.nuevo.spa.taskmanager.domain.entity.TaskStatusEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

class TaskEntityToTaskDtopMapperTest {

  private static final Long TASK_ID = 1L;
  private static final String TITLE = "TITLE";
  private static final String DESCRIPTION = "DESCRIPTION";
  private static final TaskEntityToTaskDtoMapper taskEntityToTaskDtoMapper =
      new TaskEntityToTaskDtoMapper();

  @Test
  void shouldMapTaskEntityToTaskDtoOk() {
    TaskEntity taskEntity = buildTaskDto();
    TaskDto expected = new TaskDto();
    expected.setStatus(TaskStatus.IN_PROGRESS);
    BeanUtils.copyProperties(expected, taskEntity);
    Assertions.assertEquals(
        expected,
        taskEntityToTaskDtoMapper.map(taskEntity),
        "Should mapper all properties from TaskEntity");
  }

  private TaskEntity buildTaskDto() {
    return TaskEntity.builder()
        .id(TASK_ID)
        .title(TITLE)
        .description(DESCRIPTION)
        .status(new TaskStatusEntity(TaskStatus.IN_PROGRESS))
        .build();
  }
}
