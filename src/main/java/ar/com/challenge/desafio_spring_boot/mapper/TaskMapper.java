package ar.com.challenge.desafio_spring_boot.mapper;

import ar.com.challenge.desafio_spring_boot.dto.TaskDto;
import ar.com.challenge.desafio_spring_boot.dto.TaskStateDto;
import ar.com.challenge.desafio_spring_boot.entity.Task;
import ar.com.challenge.desafio_spring_boot.entity.TaskState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskMapper {

    public static TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .status(
                        TaskStateDto.builder()
                                .id(task.getStatus().getId())
                                .status(task.getStatus().getStatus())
                                .build()
                ).build();
    }

    public static Task toEntity(TaskDto taskDto) {
        return Task.builder()
                .name(taskDto.getName())
                .status(
                        TaskState.builder()
                                .status(taskDto.getStatus().getStatus())
                                .build()
                ).build();
    }
}
