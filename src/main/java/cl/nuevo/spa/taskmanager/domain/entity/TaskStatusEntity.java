package cl.nuevo.spa.taskmanager.domain.entity;

import cl.nuevo.spa.taskmanager.domain.common.TaskStatus;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_status")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskStatusEntity {
  @Id private int id;
  private String name;

  public TaskStatusEntity(TaskStatus taskStatus) {
    this.id = taskStatus.ordinal();
    this.name = taskStatus.name();
  }
}
