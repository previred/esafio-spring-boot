package com.spa.taskmanager.mapper;



import com.spa.taskmanager.model.Task;
import org.openapitools.model.TaskRequest;

public class TaskMapper {

    public static org.openapitools.model.Task toApiModel(Task domainTask) {
        org.openapitools.model.Task apiTask = new org.openapitools.model.Task();
        apiTask.setId(domainTask.getId().intValue());
        apiTask.setTitle(domainTask.getTitle());
        apiTask.setDescription(domainTask.getDescription());
        apiTask.setStatus(domainTask.getStatus());
        return apiTask;
    }

    public static Task toDomainModel(TaskRequest taskRequest) {
        Task domainTask = new Task();
        domainTask.setTitle(taskRequest.getTitle());
        domainTask.setDescription(taskRequest.getDescription());
        domainTask.setStatus(taskRequest.getStatus());
        return domainTask;
    }
}


