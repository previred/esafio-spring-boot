package com.company.persistence.task;

import com.company.model.Task;
import com.company.persistence.task.status.TaskStatusEntity;
import com.company.persistence.users.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {


    public Task toModel(TaskEntity taskEntity) {
        return Task.builder()
                .uuid(taskEntity.getUuid())
                .startDate(taskEntity.getStartDate())
                .endDate(taskEntity.getEndDate())
                .description(taskEntity.getDescription())
                .taskStatusId(taskEntity.getTaskStatus()!=null ? taskEntity.getTaskStatus().getUuid() : null)
                .taskStatus(taskEntity.getTaskStatus()!=null ? taskEntity.getTaskStatus().getName() : null)
                .userId(taskEntity.getUser()!=null ? taskEntity.getUser().getUuid() : null)
                .userEmail(taskEntity.getUser()!=null ? taskEntity.getUser().getEmail() : null)
                .userName(taskEntity.getUser()!=null ? taskEntity.getUser().getName() : null)
                .build();
    }

    public TaskEntity toEntity(Task task) {
        return TaskEntity.builder()
                .uuid(task.getUuid())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .description(task.getDescription())
                .taskStatus(TaskStatusEntity.builder().uuid(task.getTaskStatusId()).build())
                .user(UserEntity.builder().uuid(task.getUserId()).build())
                .build();
    }
}
