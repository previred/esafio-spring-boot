package com.moveapps.taskmanager.mappers;

import com.moveapps.taskmanager.common.dto.TaskDTO;
import com.moveapps.taskmanager.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO task);

}
