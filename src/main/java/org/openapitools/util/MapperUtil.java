package org.openapitools.util;

import org.openapitools.dto.Pagination;
import org.openapitools.dto.Task;
import org.openapitools.dto.User;
import org.openapitools.dto.UserLogin;
import org.openapitools.entity.LoginEntity;
import org.openapitools.entity.TaskEntity;
import org.openapitools.entity.TaskStatusEntity;
import org.openapitools.entity.UserEntity;
import org.springframework.data.domain.Pageable;

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
                .loginId(userEntity.getLogin().getLoginId())
                .identificationNumber(userEntity.getIdentificationNumber())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .email(userEntity.getLogin().getEmail())
                .build();
    }

    public static LoginEntity dtoToLoginEntity(User user) {
        return LoginEntity.builder()
                .loginId(user.getLoginId())
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


    public static Pagination pageToDto(Pageable pageable, Integer totalElements) {
        var totalPages = (totalElements + pageable.getPageSize() - 1) / pageable.getPageSize();
        return Pagination.builder()
                .page(pageable.getPageNumber())
                .totalElements(totalElements)
                .totalPages(totalPages)
                .hasPrevious(pageable.hasPrevious())
                .hasNext(pageable.getPageNumber() < totalPages - 1)
                .build();
    }


    public static Task entityToTaskDto(TaskEntity taskEntity) {
        return Task.builder()
                .description(taskEntity.getDescription())
                .taskId(taskEntity.getTaskId())
                .startDate(taskEntity.getStartDate())
                .endDate(taskEntity.getEndDate())
                .taskStatus(taskEntity.getTaskStatus().getDescription())
                .userId(taskEntity.getUser().getUserId())
                .taskStatusId(taskEntity.getTaskStatus().getTaskStatusId())
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
                .taskStatus(TaskStatusEntity.builder().taskStatusId(task.getTaskStatusId()).build())
                .build();
    }
}
