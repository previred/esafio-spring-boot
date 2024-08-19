package cl.corellana.taskmanager.persistence.entities;

import cl.corellana.taskmanager.api.model.SignUpRequest;
import cl.corellana.taskmanager.api.model.TaskDto;
import cl.corellana.taskmanager.api.model.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusFromDto")
    TaskEntity dtoToEntity(TaskDto dto);
    @Mapping(target = "status", source = "status", qualifiedByName = "statusFromEntity")
    TaskDto entityToDto(TaskEntity entity);

    @Named("statusFromDto")
    default TaskStatusEntity getStatusEntity(TaskStatus status){
        return TaskStatusEntity.of(status);
    }

    @Named("statusFromEntity")
    default TaskStatus getStatus(TaskStatusEntity status){
        return TaskStatus.byId(status.getId());
    }

}
