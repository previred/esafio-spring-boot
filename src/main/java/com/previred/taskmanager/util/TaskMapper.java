package com.previred.taskmanager.util;

import com.previred.taskmanager.dto.TaskDto;
import com.previred.taskmanager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    @Mapping(source = "status.name", target = "status")
    @Mapping(source = "priority.name", target = "priority")
    @Mapping(source = "user.id", target = "userId")
    TaskDto taskToTaskDTO(Task task);

    @Mapping(source = "status", target = "status.name")
    @Mapping(source = "priority", target = "priority.name")
    @Mapping(source = "userId", target = "user.id")
    Task taskDTOToTask(TaskDto taskDTO);
}
