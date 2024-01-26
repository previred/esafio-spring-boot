package com.test.previred.infrastructure.rest.controller.task.request;

import com.test.previred.domain.model.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {


    private String id;

    @NotNull
    private String description;

    @NotNull
    private String status;

    public Task toTask() {
        return Task.builder().id(this.id).status(this.status).description(this.description).build();
    }
}
