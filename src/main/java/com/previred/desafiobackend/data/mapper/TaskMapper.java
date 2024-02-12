package com.previred.desafiobackend.data.mapper;

import com.previred.desafiobackend.data.entities.Task;
import com.previred.desafiobackend.data.entities.TaskStatus;
import com.previred.desafiobackend.data.entities.User;
import com.previred.desafiobackend.domain.dto.enums.RoleEnum;
import com.previred.desafiobackend.domain.dto.enums.TaskStatusEnum;
import com.previred.desafiobackend.domain.dto.task.request.CreateTask;
import com.previred.desafiobackend.domain.dto.task.response.GetTask;
import com.previred.desafiobackend.domain.dto.user.response.GetUser;

import java.time.LocalDateTime;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

public class TaskMapper {

    public static GetTask taskDtoFromEntity(Task task) {
        return GetTask.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatusEnum.valueOf(task.getTaskStatus().getStatus()))
                .identifier(task.getId())
                .creationTimestamp(task.getCreationDate())
                .lastUpdateTimestamp(task.getLastUpdateDate())
                .userInformation(GetUser.builder()
                        .email(task.getUsers().getEmail())
                        .role(RoleEnum.valueOf(task.getUsers().getRole()))
                        .build())
                .build();
    }

    public static Task fromRequest(CreateTask request, TaskStatus status, User user) {
        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .taskStatus(status)
                .users(user)
                .build();
    }

}
