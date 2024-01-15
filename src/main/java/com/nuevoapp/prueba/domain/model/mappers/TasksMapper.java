package com.nuevoapp.prueba.domain.model.mappers;

import com.nuevoapp.prueba.domain.model.dto.TasksDto;
import com.nuevoapp.prueba.domain.model.entity.TasksEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskStatusMapper.class})
public interface TasksMapper {
    TasksDto toDto(TasksEntity entity);
    TasksEntity toEntity(TasksDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    TasksEntity updateTaskFromDto(TasksDto dto, @MappingTarget TasksEntity entity);
}
