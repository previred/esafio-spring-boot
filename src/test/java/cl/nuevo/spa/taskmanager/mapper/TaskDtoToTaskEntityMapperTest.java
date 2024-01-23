package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.common.TaskStatus;
import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import cl.nuevo.spa.taskmanager.domain.entity.TaskStatusEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

class TaskDtoToTaskEntityMapperTest {

  public static final String NEW_DESCRIPTION = "NEW_DESCRIPTION";
  private static final Long TASK_ID = 1L;
  private static final TaskDtoToTaskEntityMapper taskDtoToTaskEntityMapper =
      new TaskDtoToTaskEntityMapper();
  private static final String TITLE = "TITLE";
  private static final String DESCRIPTION = "DESCRIPTION";

  @Test
  void shouldMapTaskDtoToTaskEntityOk() {
    TaskDto taskDto = buildTaskDto();
    TaskEntity expected = new TaskEntity();
    expected.setStatus(new TaskStatusEntity(TaskStatus.IN_PROGRESS));
    BeanUtils.copyProperties(taskDto, expected);
    Assertions.assertEquals(
        expected,
        taskDtoToTaskEntityMapper.map(taskDto),
        "Should mapper all properties from TaskDto");
  }

  @Test
  void shouldUpdateMapTaskDtoToTaskEntityOk() {
    TaskDto taskDto = buildTaskDto();
    TaskEntity taskEntityInBd = new TaskEntity();
    BeanUtils.copyProperties(taskDto, taskEntityInBd);
    taskDto.setDescription(NEW_DESCRIPTION);
    TaskEntity expected = new TaskEntity();
    expected.setStatus(new TaskStatusEntity(TaskStatus.IN_PROGRESS));
    BeanUtils.copyProperties(taskDto, expected);
    Assertions.assertEquals(
        expected,
        taskDtoToTaskEntityMapper.map(taskDto, taskEntityInBd),
        "Should mapper all properties from TaskEntity and TaskDto");
  }

  private TaskDto buildTaskDto() {
    return TaskDto.builder()
        .id(TASK_ID)
        .title(TITLE)
        .description(DESCRIPTION)
        .status(TaskStatus.IN_PROGRESS)
        .build();
  }
}
