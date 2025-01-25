package com.example.desafio_spring_boot.domain.task;

import lombok.Data;
import java.util.List;

import com.example.desafio_spring_boot.domain.status_task.StatusTask;
import com.example.desafio_spring_boot.domain.user.UserResponseDto;

@Data
public class TaskResponseDto {
    private final Long id;
    private final String title;
    private final String description;
    private final UserResponseDto user;
    private final String currentStatus;
    private final List<StatusTask> statusHistory;

    public TaskResponseDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.user = new UserResponseDto(task.getIdUser().getId(), task.getIdUser().getUsername());
        this.statusHistory = task.getStatusHistory();
        this.currentStatus = ""; // task.getCurrentStatus().toString();
    }
}
