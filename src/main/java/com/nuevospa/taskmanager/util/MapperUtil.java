package com.nuevospa.taskmanager.util;

import com.nuevospa.taskmanager.dto.*;
import com.nuevospa.taskmanager.entity.*;

import java.util.Objects;

public class MapperUtil {

    public static UserEntity dtoToUserEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .identificationNumber(user.getIdentificationNumber())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }

    public static User entityToUserDto(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .identificationNumber(userEntity.getIdentificationNumber())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .email(userEntity.getLogin().getEmail())
                .build();
    }

    public static LoginEntity dtoToLoginEntity(User user) {
        return LoginEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static UserLogin entityToUserLoginDto(LoginEntity loginEntity) {
        return UserLogin.builder()
                .loginId(loginEntity.getLoginId())
                .email(loginEntity.getEmail())
                .build();
    }


    public static Task entityToTaskDto(TaskEntity taskEntity) {
        return Task.builder()
                .description(taskEntity.getDescription())
                .taskId(taskEntity.getTaskId())
                .startDate(taskEntity.getStartDate())
                .endDate(taskEntity.getEndDate())
                .taskStatus(taskEntity.getTaskStatus().getDescription())
                .email(Objects.isNull(taskEntity.getUser().getLogin()) ? null
                        : taskEntity.getUser().getLogin().getEmail())
                .name(taskEntity.getUser().getName())
                .build();
    }

    public static TaskEntity dtoToTaskEntity(Task task) {
        return TaskEntity.builder()
                .description(task.getDescription())
                .taskId(task.getTaskId())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .user(UserEntity.builder().userId(task.getUserId()).build())
                .taskStatus(TaskStatusEntity.builder().taskStatusEntity(task.getTaskStatusId()).build())
                .build();
    }
}
