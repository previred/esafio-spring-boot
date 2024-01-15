package com.challenge.spa.adapter.in.web.task.resource;


import com.challenge.spa.domain.StatusTask;
import com.challenge.spa.domain.Task;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record TaskRequest(
        @Schema(description = "Status for task, default is CREATED.",
                example = "ATTENDED (not required, default is CREATED)")
        String status,
        @Schema(description = "Name for task.",
                example = "Tarea name")
        @NotNull
        String name,
        @Schema(description = "Description for task",
                example = "Tarea descripción")
        @NotNull
        String description
) {
  public Task toDomain() {
    var domain = new Task();
    domain.setNameTask(name);
    domain.setDescriptionTask(description);
    if(status != null)
      domain.setStatusTask(new StatusTask(status));
    return domain;
  }
}
