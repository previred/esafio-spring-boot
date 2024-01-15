package com.challenge.spa.adapter.in.web.task.resource;

import com.challenge.spa.application.shared.Status;
import com.challenge.spa.domain.StatusTask;

import java.util.UUID;

public record StatusTaskRequest(
        Status status
) {
  public StatusTask toDomain() {
    return new StatusTask(status);
  }
}
