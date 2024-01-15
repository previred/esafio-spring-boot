package com.challenge.spa.adapter.in.web.task.resource;

import com.challenge.spa.domain.Task;

public record TaskResponse(
  String id,
  String status,
  String name,
  String description
) {
  public static TaskResponse fromDomain(Task task) {
    return new TaskResponse(
      task.getId(),
      task.getStatusTask().getStatus().name(),
      task.getNameTask(),
      task.getDescriptionTask()
    );
  }
}
