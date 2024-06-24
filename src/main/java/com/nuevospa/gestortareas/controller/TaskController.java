package com.nuevospa.gestortareas.controller;

import com.nuevospa.gestortareas.dto.CreateTaskDTO;
import com.nuevospa.gestortareas.dto.TaskResponseDTO;
import com.nuevospa.gestortareas.dto.UpdateTaskDTO;
import com.nuevospa.gestortareas.entity.Task;
import com.nuevospa.gestortareas.entity.User;
import com.nuevospa.gestortareas.service.TaskService;
import com.nuevospa.gestortareas.repository.TaskStatusRepository;
import com.nuevospa.gestortareas.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Controller", description = "Endpoints para la gestión de tareas")
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;

    public TaskController(TaskService taskService, UserRepository userRepository, TaskStatusRepository taskStatusRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Operation(summary = "Obtener todas las tareas", description = "Muestra todas las tareas creadas y almacenadas en la Base de Datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tareas encontradas"),
            @ApiResponse(responseCode = "404", description = "No existen tareas")
    })
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Obtener una tarea por ID", description = "Obtiene los detalles de una tarea específica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(
            @Parameter(description = "ID de la tarea a buscar", required = true)
            @PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva tarea", description = "Crea una nueva tarea con los detalles proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody @Valid CreateTaskDTO createTaskDTO) {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Task task = new Task();
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());
        task.setInformation(createTaskDTO.getInformation());
        task.setUser(currentUser);

        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Operation(summary = "Actualizar una tarea", description = "Actualiza una tarea existente por su ID, debe agregar informacion a la tarea y un nuevo estado (Pendiente, En Proceso, Completada).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @Parameter(description = "ID de la tarea a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos detalles de la tarea", required = true)
            @RequestBody @Valid UpdateTaskDTO updateTaskDTO) {
        Task updatedTask = taskService.updateTask(id, updateTaskDTO);
        TaskResponseDTO responseDTO = new TaskResponseDTO(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getInformation(),
                updatedTask.getUser().getUsername(),
                updatedTask.getStatus().getName()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(
            @Parameter(description = "ID de la tarea a eliminar", required = true)
            @PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok("Tarea eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
        }
    }
}
