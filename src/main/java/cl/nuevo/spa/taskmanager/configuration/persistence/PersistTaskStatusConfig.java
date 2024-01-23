package cl.nuevo.spa.taskmanager.configuration.persistence;

import cl.nuevo.spa.taskmanager.domain.common.TaskStatus;
import cl.nuevo.spa.taskmanager.domain.entity.TaskStatusEntity;
import cl.nuevo.spa.taskmanager.repository.TaskStatusRepository;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PersistTaskStatusConfig {

  private final TaskStatusRepository taskStatusRepository;

  @PostConstruct
  public void init() {
    Arrays.stream(TaskStatus.values())
        .forEach(
            taskStatus ->
                taskStatusRepository.save(
                    TaskStatusEntity.builder()
                        .id(taskStatus.ordinal())
                        .name(taskStatus.name())
                        .build()));
  }
}
