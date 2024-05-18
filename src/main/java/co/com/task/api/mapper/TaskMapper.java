package co.com.task.api.mapper;

import co.com.task.api.domain.Task;
import co.com.task.api.domain.User;
import co.com.task.api.dto.TaskDTO;
import co.com.task.api.utility.Utilities;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskMapper {

    public static Task updateTaskDtoToTaskDomain(Task taskDomain, TaskDTO taskDto) {
	return Task.builder().idTask(taskDto.getIdTask()).description(taskDto.getDescription())
		.status(taskDto.getStatus()).created(taskDomain.getCreated()).modified(taskDomain.getModified())
		.user(User.builder().idUser(taskDto.getIdUser()).build()).build();
    }

    public static TaskDTO taskDomainToTaskDto(Task taskDomain) {
	return TaskDTO.builder()
		.idTask(taskDomain.getIdTask())
		.description(taskDomain.getDescription())
		.name(taskDomain.getUser().getName())
		.status(taskDomain.getStatus())
		.created(Utilities.convertToDateViaInstant(taskDomain.getCreated()))
		.modified(Utilities.convertToDateViaInstant(taskDomain.getModified()))
		.email(taskDomain.getUser().getEmail())
		.idUser(taskDomain.getUser().getIdUser())
		.build();
    }

    public static Task taskDtoToTaskDomain(TaskDTO taskDto) {
	return Task.builder()
		.idTask(taskDto.getIdTask())
		.description(taskDto.getDescription())
		.status(taskDto.getStatus())
		.user(User.builder().idUser(taskDto.getIdUser()).build())
		.build();
    }

}
