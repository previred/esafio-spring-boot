package cl.corellana.taskmanager.service;

import cl.corellana.taskmanager.api.model.TaskDto;

import java.util.List;

public interface GetTaskService {

    List<TaskDto> getAllTasks();

    TaskDto getById(Integer id);
}
