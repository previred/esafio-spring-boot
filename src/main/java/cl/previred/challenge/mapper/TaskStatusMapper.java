package cl.previred.challenge.mapper;

import cl.previred.challenge.dto.TaskStatusDto;
import cl.previred.challenge.dto.UserDto;
import cl.previred.challenge.model.TaskStatus;
import cl.previred.challenge.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskStatusMapper {

  TaskStatusMapper INSTANCE = Mappers.getMapper(TaskStatusMapper.class);

  TaskStatus toEntity(TaskStatusDto obj);

  TaskStatusDto toDto(TaskStatus obj);

}
