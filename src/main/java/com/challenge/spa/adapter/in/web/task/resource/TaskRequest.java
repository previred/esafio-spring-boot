package com.challenge.spa.adapter.in.web.task.resource;

import com.challenge.spa.application.shared.Status;
import com.challenge.spa.domain.StatusTask;
import com.challenge.spa.domain.Task;

public record TaskRequest(
        String status,
        String name,
        String description
) {
  public Task toDomain() {
    var domain = new Task();
    domain.setNameTask(name);
    domain.setDescriptionTask(description);
    if(status != null)
      domain.setStatusTask(new StatusTask(Status.valueOf(status)));
    return domain;
  }
}
