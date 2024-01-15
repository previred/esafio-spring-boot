package com.challenge.spa.adapter.out.persistence.task;


import com.challenge.spa.adapter.out.persistence.statustask.StatusTaskEntity;
import com.challenge.spa.adapter.out.persistence.user.UserEntity;
import com.challenge.spa.domain.Task;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
public class TaskEntity {
  @Id
  private String id = UUID.randomUUID().toString();

  @ManyToOne
  @JoinColumn(name = "status_id", referencedColumnName = "id")
  private StatusTaskEntity statusTask;

  private String name;
  private String description;

  public Task toDomain() {
    return new Task(
            id,
            statusTask.toDomain(),
            name,
            description
    );
  }

  public static TaskEntity fromDomain(Task task) {
    var entity = new TaskEntity();
    if(task.getId() != null)
      entity.setId(task.getId());
    entity.setStatusTask(StatusTaskEntity.fromDomain(task.getStatusTask()));
    entity.setName(task.getNameTask());
    entity.setDescription(task.getDescriptionTask());
    return entity;
  }
}
