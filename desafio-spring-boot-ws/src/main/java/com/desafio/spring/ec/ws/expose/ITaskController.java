package com.desafio.spring.ec.ws.expose;

import com.desafio.spring.ec.ds.dto.TasksTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.desafio.spring.ec.ws.common.SwaggerConstants.TASKS_DESCRIPTION;
import static com.desafio.spring.ec.ws.common.SwaggerConstants.TASKS_TAG;

@Tag(name = TASKS_TAG, description = TASKS_DESCRIPTION)
@RequestMapping("/api/tasks")
public interface ITaskController {

    @Operation(summary = "API to create Tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Task already exists")
    })
    @PostMapping
    ResponseEntity<TasksTO> createTask(@RequestBody TasksTO task) throws Exception;

    @Operation(summary = "API to update Tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<TasksTO> updateTask(@RequestBody TasksTO task, @PathVariable Long id) throws Exception;

    @Operation(summary = "API to get Task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<TasksTO> getTask(@PathVariable Long id) throws Exception;

    @Operation(summary = "API to get all Tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    @GetMapping
    ResponseEntity<List<TasksTO>> getAllTasks() throws Exception;

    @Operation(summary = "API to delete Task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(Long id) throws Exception;

}