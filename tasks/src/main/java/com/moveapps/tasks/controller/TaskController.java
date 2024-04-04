package com.moveapps.tasks.controller;

import com.moveapps.tasks.model.ErrorResponse;
import com.moveapps.tasks.model.Task;
import com.moveapps.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Guardar una tarea
    @Operation(summary = "Guardar una nueva tarea")
    @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente")
    @ApiResponse(responseCode = "401", description = "Error al crear tarea", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @PostMapping("/save")
    public ResponseEntity<Task> save(@RequestBody Task task){
        Task taskSaved = taskService.save(task);
        return ResponseEntity.status(201).body(taskSaved);
    }

    @Operation(summary = "Obtener todas las tareas almacenadas")
    @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida exitosamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
    })
    @ApiResponse(responseCode = "404", description = "No se encontraron tareas", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // Obtener una tarea por su ID
    @Operation(summary = "Obtener una tarea por su ID")
    @ApiResponse(responseCode = "200", description = "Tarea encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
    })
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable @Parameter(description = "ID de la tarea") Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // Actualizar una tarea
    @Operation(summary = "Actualizar una tarea existente")
    @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente")
    @ApiResponse(responseCode = "400", description = "Petición inválida")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @Operation(summary = "Eliminar una tarea por su ID")
    @ApiResponse(responseCode = "204", description = "Tarea eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable @Parameter(description = "ID de la tarea") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (taskService.deleteTask(id)) {
                response.put("message", "Tarea con el ID: " + id + " fue eliminada con éxito");
                return ResponseEntity.status(204).body(response);
            } else {
                response.put("message", "Tarea con el ID: " + id + " no encontrada");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
