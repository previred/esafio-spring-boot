package jlillor.ms.tasks.manager.mappers;

import jlillor.ms.tasks.manager.dtos.NewTaskRequest;
import jlillor.ms.tasks.manager.dtos.TaskResponse;
import jlillor.ms.tasks.manager.entities.Task;
import jlillor.ms.tasks.manager.entities.TaskStatus;
import jlillor.ms.tasks.manager.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper de tareas.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Mapper
public interface TaskMapper {

    /**
     * Mapea un objeto {@link NewTaskRequest} a un objeto {@link Task}.
     *
     * @param request {@link NewTaskRequest} objeto a mapear
     * @param user {@link User} usuario de la tarea
     * @param status {@link TaskStatus} estado de la tarea
     * @return tarea {@link Task} tarea mapeada
     */
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "title", source = "request.title"),
            @Mapping(target = "description", source = "request.description"),
            @Mapping(target = "startDate", source = "request.startDate", defaultExpression ="java(java.time.LocalDateTime.now())"),
            @Mapping(target = "endDate", ignore = true),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "status", source = "status")
    })
    Task toEntity(NewTaskRequest request, User user, TaskStatus status);

    /**
     * Mapea un objeto {@link Task} a un objeto {@link TaskResponse}.
     *
     * @param task {@link Task} objeto a mapear
     * @return tarea {@link TaskResponse} tarea mapeada
     */
    @Mappings({
            @Mapping(target = "idTask", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "startDate", source = "startDate"),
            @Mapping(target = "endDate", source = "endDate"),
            @Mapping(target = "status", source = "status.status"),
            @Mapping(target = "user", source = "user.name"),
    })
    TaskResponse fromEntity(Task task);

}
