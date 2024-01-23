package cl.nuevo.spa.taskmanager.domain.common;

import java.util.stream.Stream;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
  TODO("TODO"),
  IN_PROGRESS("IN_PROGRESS"),
  DONE("DONE");

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final String status;

  public static TaskStatus of(String status) {
    return Stream.of(TaskStatus.values())
        .filter(taskStatusEnum -> taskStatusEnum.getStatus().equalsIgnoreCase(status))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
