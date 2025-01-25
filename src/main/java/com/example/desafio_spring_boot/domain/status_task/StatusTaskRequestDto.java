package com.example.desafio_spring_boot.domain.status_task;

import java.time.LocalDateTime;

import com.example.desafio_spring_boot.domain.task.Task;

import lombok.Data;

@Data
public class StatusTaskRequestDto {
    StatusNames statusName;
    Long taskId;

    public StatusTask toStatusTask() {
        Task task = new Task();
        task.setId(taskId);
        StatusTask status = new StatusTask();
        status.setStatusName(statusName);
        status.setTask(task);
        status.setChangedAt(LocalDateTime.now());
        return status;
    }

}
