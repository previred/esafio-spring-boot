package com.challenge.spa.adapter.out.persistence.statustask;

import com.challenge.spa.application.shared.Status;
import com.challenge.spa.domain.StatusTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusTaskEntity {
  @Id
  private String id = UUID.randomUUID().toString();
  private Status status;

  public StatusTaskEntity(Status status) {
    this.status = status;
  }

  public StatusTask toDomain() {
    return new StatusTask(id, status);
  }

  public static StatusTaskEntity fromDomain(StatusTask statusTask) {
    return new StatusTaskEntity(statusTask.getId(), statusTask.getStatus());
  }
}
