package cl.nuevo.spa.taskmanager.controller.rest;

import cl.nuevo.spa.taskmanager.domain.common.ApiBaseExceptionDetail;
import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  /**
   * Retrieve all tasks.
   *
   * @return List of TaskDto
   */
  @Operation(summary = "Get all tasks", tags = "tasks")
  @ApiResponse(
      responseCode = "200",
      description = "Successful operation",
      content = {
        @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = TaskDto.class)))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(responseCode = "500", description = "Internal server error")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('READ_ALL_USER')")
  @GetMapping
  public ResponseEntity<List<TaskDto>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }

  /**
   * Retrieve a task by ID.
   *
   * @param taskId Task ID
   * @return TaskDto
   */
  @Operation(summary = "Get a task by ID", tags = "tasks")
  @ApiResponse(
      responseCode = "200",
      description = "Successful operation",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "404",
      description = "User not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('READ_ALL_TASK')")
  @GetMapping("/{taskId}")
  public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
    TaskDto task = taskService.getTaskById(taskId);
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  /**
   * Create a new task.
   *
   * @param task TaskDto for creating a new task
   * @return Created TaskDto
   */
  @Operation(summary = "Create a new task", tags = "tasks")
  @ApiResponse(
      responseCode = "201",
      description = "Task created successfully",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid task details",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping(consumes = "application/json", produces = "application/json")
  @PreAuthorize("hasAuthority('CREATE_ONE_TASK')")
  public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto task) {
    TaskDto createdTask = taskService.createTask(task);
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
  }

  /**
   * Update an existing task.
   *
   * @param task Updated TaskDto
   * @return Updated TaskDto
   */
  @Operation(summary = "Update an existing task", tags = "tasks")
  @ApiResponse(
      responseCode = "200",
      description = "Task updated successfully",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid task details",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "404",
      description = "Task not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('UPDATE_ONE_TASK')")
  @PutMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto task) {
    TaskDto updatedTask = taskService.updateTask(task);
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
  }

  /**
   * Delete a task by ID.
   *
   * @param taskId Task ID to be deleted
   * @return No content response
   */
  @Operation(summary = "Delete a task by ID", tags = "tasks")
  @ApiResponse(responseCode = "204", description = "Task deleted successfully")
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "404",
      description = "Task not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @PreAuthorize("hasAuthority('DELETE_ONE_TASK')")
  @DeleteMapping("/{taskId}")
  @SecurityRequirement(name = "Bearer Authentication")
  public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
    taskService.deleteTask(taskId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
