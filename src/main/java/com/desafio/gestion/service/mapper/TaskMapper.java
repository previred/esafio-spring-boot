package com.desafio.gestion.service.mapper;

import com.desafio.gestion.domain.Task;
import com.desafio.gestion.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "status.name", target = "status")
    TaskDTO toDto(Task task);

    List<TaskDTO> tasksToTaskDtos(List<Task> tasks);
}
