package com.example.service.impl;

import com.example.entity.StateTask;
import com.example.entity.Task;
import com.example.repository.TaskRepository;
import com.example.repository.TaskStateRepository;
import com.example.task_management.TasksApiDelegate;
import com.example.task_management.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TasksApiDelegate {

    private final TaskRepository taskRepository;
    private final TaskStateRepository taskStateRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskStateRepository taskStateRepository) {
        this.taskRepository = taskRepository;
        this.taskStateRepository = taskStateRepository;
    }

    @Override
    public ResponseEntity<CreateTaskResponse> create(CreateTaskRequest request) {
        Task task = Task.builder()
                .name(request.getName())
                .build();

        Task taskSaved = taskRepository.save(task);

        StateTask stateTask = StateTask.builder()
                .task(taskSaved)
                .name("created")
                .build();

        taskStateRepository.save(stateTask);

        CreateTaskDataResponse createTaskDataResponse = new CreateTaskDataResponse();
        createTaskDataResponse.setId(taskSaved.getTaskId().intValue());

        Metadata metadata = new Metadata();
        metadata.setMessage("Create success");
        metadata.setStatus(200);

        CreateTaskResponse response = new CreateTaskResponse();
        response.setData(createTaskDataResponse);
        response.setMetadata(metadata);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteTaskResponse> delete(BigDecimal id) {
        List<StateTask> stateTaskSaved = taskStateRepository.findByTaskId(id.longValue());

        StateTask stateTask = StateTask.builder()
                .task(stateTaskSaved.get(0).getTask())
                .name("deleted")
                .build();

        taskStateRepository.save(stateTask);

        Metadata metadata = new Metadata();
        metadata.setMessage("Delete success");
        metadata.setStatus(200);

        DeleteTaskResponse response = new DeleteTaskResponse();
        response.setMetadata(metadata);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetTaskResponse> get(Integer id) {
        Optional<Task> task = taskRepository.findById(id.longValue());

        Metadata metadata = new Metadata();
        metadata.setMessage("Get success");
        metadata.setStatus(200);

        GetTaskDataResponse getTaskDataResponse = new GetTaskDataResponse();
        getTaskDataResponse.setId(task.get().getTaskId().intValue());
        getTaskDataResponse.setName(task.get().getName());
        getTaskDataResponse.setStatus(
                task.get().getStateTasks().stream().map(StateTask::getName).collect(Collectors.toList())
        );

        GetTaskResponse response = new GetTaskResponse();
        response.setData(getTaskDataResponse);
        response.setMetadata(metadata);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateTaskResponse> udpate(UpdateTaskRequest request) {
        Optional<Task> task = taskRepository.findById(request.getId().longValue());

        task.get().setName(request.getName());

        taskRepository.save(task.get());

        Metadata metadata = new Metadata();
        metadata.setMessage("Update success");
        metadata.setStatus(200);

        UpdateTaskResponse response = new UpdateTaskResponse();
        response.setMetadata(metadata);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
