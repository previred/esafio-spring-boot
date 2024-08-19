package cl.corellana.taskmanager.service;

import cl.corellana.taskmanager.api.model.CreateTaskRequest;

public interface CreateTaskService {

    void create(CreateTaskRequest request);
}
