package cl.corellana.taskmanager.service;

import cl.corellana.taskmanager.api.model.TaskDto;

public interface UpdateTaskService {

    void update(TaskDto request);
}
