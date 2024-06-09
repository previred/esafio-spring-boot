package com.test.moveapps.controller;

import com.test.moveapps.domain.dto.TaskDto;
import com.test.moveapps.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.path}")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary="Add task - only need the task name")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "${task.crud.add}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(taskDto.getTaskName()));
    }

    @Operation(summary="List of tasks")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "${task.crud.list}")
    public ResponseEntity<List<TaskDto>> listTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

}
