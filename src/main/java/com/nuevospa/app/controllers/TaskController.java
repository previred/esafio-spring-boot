package com.nuevospa.app.controllers;

import com.nuevospa.app.dtos.request.TaskRequestDto;
import com.nuevospa.app.entities.Task;
import com.nuevospa.app.models.ResponseError;
import com.nuevospa.app.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/tasks", produces = "application/json")
@Tag(name = "Tasks")
@ApiResponse(
        responseCode = "401",
        description = "Required autentication.",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResponseError.class)
        )
)
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create task", description = "Create a new task.")
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request.",
            content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    public Task createTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        return taskService.createTask(taskRequestDto);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all tasks", description = "Get all existing tasks.")
    public List<Task> getAllTask() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get task by ID", description = "Get a specific task by its ID.")
    @ApiResponse(
            responseCode = "404",
            description = "Task no found.",
            content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    public Task getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update task", description = "Update an existing task.")
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request.",
            content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task no found.",
            content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    public Task updateTask(@PathVariable long id, @RequestBody @Valid TaskRequestDto taskRequestDto) {
        return taskService.updateTask(id, taskRequestDto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete task", description = "Delete an existing task.")
    @ApiResponse(
            responseCode = "404",
            description = "Task no found.",
            content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    public Task deleteTask(@PathVariable long id) {
        return taskService.deleteTask(id);

    }

}
