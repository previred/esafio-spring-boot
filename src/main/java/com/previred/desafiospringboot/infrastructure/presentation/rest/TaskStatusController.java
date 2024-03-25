package com.previred.desafiospringboot.infrastructure.presentation.rest;

import com.previred.desafiospringboot.application.TaskStatusService;
import com.previred.desafiospringboot.domain.model.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task-status")
public class TaskStatusController {

    private final TaskStatusService taskStatusService;

    public TaskStatusController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @GetMapping
    public ResponseEntity<List<TaskStatus>> getAllTaskStatus() {
        List<TaskStatus> taskStatusList = taskStatusService.getAllTaskStatus();
        return new ResponseEntity<>(taskStatusList, HttpStatus.OK);
    }
}

