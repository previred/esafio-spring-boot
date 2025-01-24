package cl.previred.tasksapi.mappers;

import cl.previred.tasksapi.model.TaskModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Task;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    /**
     *
     * @param entity
     * @return
     */
    @Mapping(target = "taskId", source = "taskId")
    @Mapping(target = "taskName", source = "taskName")
    @Mapping(target = "taskDescription", source = "taskDescription")
    @Mapping(target = "taskStatusId", source = "taskStatus.taskStatusId")
    @Mapping(target = "taskStatusName", source = "taskStatus.taskStatusName")
    Task entityToDTO(TaskModel entity);

    /**
     *
     * @param dto
     * @return
     */
    @Mapping(target = "taskId", source = "taskId")
    @Mapping(target = "taskName", source = "taskName")
    @Mapping(target = "taskDescription", source = "taskDescription")
    @Mapping(target = "taskStatus.taskStatusId", source = "taskStatusId")
    @Mapping(target = "taskStatus.taskStatusName", source = "taskStatusName")
    TaskModel dtoToEntity(Task dto);

    // Conversión de String a JsonNullable<String>
    default JsonNullable<String> stringToJsonNullable(String value) {
        return value == null ? JsonNullable.undefined() : JsonNullable.of(value);
    }

    // Conversión de JsonNullable<String> a String
    default String jsonNullableToString(JsonNullable<String> jsonNullable) {
        return jsonNullable == null || !jsonNullable.isPresent() ? null : jsonNullable.get();
    }

    /**
     *
     * @param entityList
     * @return
     */
    List<Task> entityListToDTOList(List<TaskModel> entityList);

    /**
     *
     * @param dtoList
     * @return
     */
    List<TaskModel> dtoListToEntityList(List<Task> dtoList);
}
