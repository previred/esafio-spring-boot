package com.spa.task.controller;

import com.spa.task.converter.TaskConverter;
import com.spa.task.dto.ErrorDTO;
import com.spa.task.dto.TaskDTO;
import com.spa.task.entity.Task;
import com.spa.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
@Tag(name = "Task", description = "Manage tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskConverter taskConverter;

    @Operation(summary = "get all task created")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<TaskDTO>> listTask(){
        return ResponseEntity.ok(taskConverter.fromEntity(taskService.findAll()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content) ,
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @Operation(summary = "get task by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@Parameter(description = "id of task to be searched") @PathVariable Long id){

        try {
            Task task = taskService.findById(id);
            return ResponseEntity.ok(taskConverter.fromEntity(task));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(summary = "create tasks", description = "Create new task with a structure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "task created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "invalid request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@Valid @RequestBody TaskDTO dto) {

        Task task = taskService.create(taskConverter.fromDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskConverter.fromEntity(task));

    }

    @Operation(summary = "update tasks", description = "update task with a structure and id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "task updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "invalid request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody TaskDTO dto,
                                              @Parameter(description = "id of task to be updated") @PathVariable Long id){

        Task taskUpdate = taskConverter.fromDTO(dto);
        TaskDTO taskDTO = taskConverter.fromEntity(taskService.update(taskUpdate, id));
        return ResponseEntity.ok(taskDTO);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the task"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content) ,
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "delete task by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@Parameter(description = "id of task to be deleted") @PathVariable Long id){

        taskService.delete(id);
        return ResponseEntity.ok().build();

    }

}
