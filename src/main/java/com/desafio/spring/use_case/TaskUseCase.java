package com.desafio.spring.use_case;

import com.desafio.spring.service.impl.TaskServiceImpl;
import com.desafio.spring.service.impl.TaskStatusServiceImpl;
import com.desafio.spring.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.openapitools.api.TaskApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskUseCase implements TaskApi {

    private TaskServiceImpl taskService;
    private TaskStatusServiceImpl taskStatusService;
    private UserServiceImpl userService;


    public TaskUseCase(TaskServiceImpl taskServiceImpl, TaskStatusServiceImpl taskStatusServiceImpl, UserServiceImpl userServiceImpl) {
        this.taskService = taskServiceImpl;
        this.taskStatusService = taskStatusServiceImpl;
        this.userService = userServiceImpl;
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Task>> getAllTask(){
        List<org.openapitools.model.Task>  taskList = this.taskService.getAll();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<org.openapitools.model.Task> getTaskById(
            @Parameter(name = "id", description = "ID Task", required = true, in = ParameterIn.PATH) @PathVariable("id") String id
    ) {
        org.openapitools.model.Task  task = this.taskService.getById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTaskById(
            @Parameter(name = "id", description = "ID Task", required = true, in = ParameterIn.PATH) @PathVariable("id") String id
    ){
        this.taskService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> addTask(
            @Parameter(name = "Task", description = "Create Task", required = true) @Valid @RequestBody org.openapitools.model.Task task
    ) {
        long idUser = this.userService.getByName(task.getUser()).getId();
        long idStatus = this.taskStatusService.getTaskStatusByName(task.getStatus()).getId();

        this.taskService.add(task,idUser, idStatus);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<org.openapitools.model.Task> taskPut(
            @Parameter(name = "Task", description = "", required = true) @Valid @RequestBody org.openapitools.model.Task task
    ) {
        long idUser = this.userService.getByName(task.getUser()).getId();
        long idStatus = this.taskStatusService.getTaskStatusByName(task.getStatus()).getId();


        return new ResponseEntity<>(    this.taskService.update(task,idUser, idStatus), HttpStatus.OK);
    }

}
