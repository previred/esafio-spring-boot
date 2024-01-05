package com.moveapps.taskmanager.mappers;

import com.moveapps.taskmanager.common.dto.TaskStateDTO;
import com.moveapps.taskmanager.entity.TaskState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskStateMapper {

    TaskStateDTO toDto(TaskState taskState);
    TaskState toEntity(TaskStateDTO taskStateDTO);

}
