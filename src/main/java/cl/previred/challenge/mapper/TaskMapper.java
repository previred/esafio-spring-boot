package cl.previred.challenge.mapper;

import cl.previred.challenge.dto.TaskDto;
import cl.previred.challenge.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

  TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

  Task toEntity(TaskDto obj);

  TaskDto toDto(Task obj);

}
