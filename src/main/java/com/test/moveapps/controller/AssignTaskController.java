package com.test.moveapps.controller;

import com.test.moveapps.domain.dto.AssignTaskDto;
import com.test.moveapps.domain.dto.AssignTaskUpdateDto;
import com.test.moveapps.service.AssignTaskService;
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
public class AssignTaskController {

        @Autowired
        private AssignTaskService assignTaskService;

    @Operation(summary="Assign task to user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "${assign.task.crud.add}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> assignTask(@RequestBody AssignTaskDto assignTaskDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(assignTaskService.assignTaskUser(assignTaskDto));
    }

    @Operation(summary="List of task asigned")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "${assign.task.crud.list}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AssignTaskDto>> assignTaskList() {
        return ResponseEntity.status(HttpStatus.OK).body(assignTaskService.assignTaskList());
    }

    @Operation(summary="Update status of task")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "${assign.task.crud.update}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> assignTaskUpdate(@RequestBody AssignTaskUpdateDto assignTaskUpdateDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(assignTaskService.assignTaskUpdate(assignTaskUpdateDto));
    }



}

