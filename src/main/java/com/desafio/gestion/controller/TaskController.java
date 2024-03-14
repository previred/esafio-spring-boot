package com.desafio.gestion.controller;

import com.desafio.gestion.dto.TaskDTO;
import com.desafio.gestion.dto.TaskPayload;
import com.desafio.gestion.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Tareas", description = "Administración API Tareas")
@RestController
@RequestMapping("/gestion/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Obtener tareas de un usuario",
            description = "Obtener una tarea por id usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Tarea con el id usuario dado no fue encontrada.", content = { @Content(schema = @Schema()) })})
    public ResponseEntity<List<TaskDTO>> getTasksByUserId(@Parameter(description = "Usuario id") @PathVariable Long userId) {
        List<TaskDTO> tasks = taskService.findByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    @Operation(
            summary = "Obtener tarea",
            description = "Obtener una tarea por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Tarea con id dado no fue encontrada.", content = { @Content(schema = @Schema()) })})
    public ResponseEntity<TaskDTO> getTaskById(@Parameter(description = "Tarea id") @PathVariable Long taskId) {
        TaskDTO task = taskService.findById(taskId);
        return ResponseEntity.ok(task);
    }

    @GetMapping()
    @Operation(
            summary = "Obtener todas las tareas",
            description = "Obtener todas las tareas registradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)), mediaType = "application/json") }),
    })
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    @Operation(
            summary = "Crear tarea",
            description = "Crear una nueva tarea")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente", content = { @Content(schema = @Schema(implementation = TaskPayload.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud de creación de tarea inválida")})
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskPayload taskPayload) {
        TaskDTO createdTask = taskService.createTask(taskPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{taskId}")
    @Operation(
            summary = "Actualizar tarea",
            description = "Actualizar una tarea existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente", content = { @Content(schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud de actualización de tarea inválida"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")})
    public ResponseEntity<TaskDTO> updateTask(@Parameter(description = "Tarea id") @PathVariable Long taskId, @Valid @RequestBody TaskDTO task) {
        TaskDTO updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    @Operation(
            summary = "Eliminar tarea",
            description = "Eliminar una tarea existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarea eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")})
    public ResponseEntity<Void> deleteTask(@Parameter(description = "Tarea id") @PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }
}
