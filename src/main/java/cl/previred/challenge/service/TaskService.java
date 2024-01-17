package cl.previred.challenge.service;

import cl.previred.challenge.config.security.JwtUserData;
import cl.previred.challenge.dto.TaskDto;
import cl.previred.challenge.dto.request.AssignTaskToRequest;
import cl.previred.challenge.dto.request.ChangeTaskStatusRequest;
import cl.previred.challenge.dto.request.NewTaskRequest;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> taskList();
    List<TaskDto> taskListByUser(Integer userId);
    Optional<TaskDto> createTask(NewTaskRequest newTaskRequest, JwtUserData userData);
    Optional<TaskDto> updateTaskContent(NewTaskRequest newTaskRequest, Integer taskId, JwtUserData userData);
    Optional<TaskDto> assignTaskTo(AssignTaskToRequest assignTaskToRequest, Integer taskId);
    Optional<TaskDto> changeTaskStatus(ChangeTaskStatusRequest taskStatusRequest, Integer taskId);
    Optional<Void> deleteTask(Integer taskId, JwtUserData userData);
}
