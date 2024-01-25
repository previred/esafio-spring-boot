package com.spa.task.converter;

import com.spa.task.dto.TaskDTO;
import com.spa.task.entity.Task;
import com.spa.task.entity.TaskStatus;
import com.spa.task.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter implements Converter<Task, TaskDTO>{

    @Override
    public TaskDTO fromEntity(Task entity) {
        return TaskDTO.builder()
                .id(entity.getId())
                .task(entity.getTask())
                .statusId(entity.getStatus().getId())
                .status(entity.getStatus().getName())
                .user(entity.getUser().getUsername())
                .creationDate(entity.getCreationDate().toString())
                .updatedDate(entity.getLastModifiedDate().toString())
                .build();
    }

    @Override
    public Task fromDTO(TaskDTO dto) {
        return Task.builder()
                .task(dto.getTask())
                .status(TaskStatus.builder()
                        .id(dto.getStatusId())
                        .build())
                .user(User.builder()
                        .username(dto.getUser())
                        .build())
                .build();
    }

    @Override
    public List<TaskDTO> fromEntity(List<Task> entityList) {
        return entityList.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
