package com.nuevoapp.prueba.domain.model.mappers;

import com.nuevoapp.prueba.domain.model.dto.TaskStatusDto;
import com.nuevoapp.prueba.domain.model.entity.TaskStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskStatusMapper {
    TaskStatusDto toDto(TaskStatusEntity entity);
    TaskStatusEntity toEntity(TaskStatusDto dto);

}
