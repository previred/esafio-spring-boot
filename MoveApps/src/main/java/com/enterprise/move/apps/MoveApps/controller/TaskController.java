package com.enterprise.move.apps.MoveApps.controller;


import com.enterprise.move.apps.MoveApps.service.TaskService;
import org.openapitools.api.TaskApi;
import org.openapitools.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import org.openapitools.model.TaskSuccessful;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class TaskController implements TaskApi {


    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    private TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<Task>> getTask(){

        List<Task>  taskList =taskService.getTask();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskSuccessful> saveTask(@Valid  @RequestBody Task task){

        TaskSuccessful taskSuccessful = taskService.createTask(task);
        return new ResponseEntity<>(taskSuccessful, HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<TaskSuccessful> taskIdPut(@PathVariable("id") Integer id, Task task) {
        TaskSuccessful taskSuccessful = taskService.updateTask(id,task);
        return new ResponseEntity<>(taskSuccessful, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> taskIdDelete(@PathVariable("id") Integer id){

        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
