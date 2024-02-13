package cl.nuevospa.taskmanagement.controller;

import cl.nuevospa.taskmanagement.annotation.Trace;
import cl.nuevospa.taskmanagement.annotation.ValidRequest;
import cl.nuevospa.taskmanagement.dto.MessageResponseDTO;
import cl.nuevospa.taskmanagement.dto.TaskRequestDTO;
import cl.nuevospa.taskmanagement.dto.TaskResponseDTO;
import cl.nuevospa.taskmanagement.service.TaskService;
import cl.nuevospa.taskmanagement.util.MessagesConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Tag(name = "Task Management", description = "API para la gestión de tareas")
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Crea una nueva tarea",
            description = "Permite la creación de una nueva tarea en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @Trace
    @ValidRequest
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskDTO, BindingResult bindingResult) throws Exception {
        TaskResponseDTO taskResponse = taskService.createTask(taskDTO);
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todas las tareas",
            description = "Retorna una lista de todas las tareas registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de tareas obtenido exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO[].class)))
            })
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Obtiene una tarea por ID",
            description = "Retorna los detalles de una tarea específica dado su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarea encontrada",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
            })
    @Trace
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable UUID taskId) {
        TaskResponseDTO taskResponse = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Actualiza una tarea existente",
            description = "Permite actualizar los detalles de una tarea existente dado su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @Trace
    @ValidRequest
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID taskId, @Valid @RequestBody TaskRequestDTO taskDTO,
                                                      BindingResult bindingResult) {
        TaskResponseDTO taskResponse = taskService.updateTask(taskId, taskDTO);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Elimina una tarea",
            description = "Permite eliminar una tarea específica dado su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MessageResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
            })
    @Trace
    @DeleteMapping("/{taskId}")
    public ResponseEntity<MessageResponseDTO> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        MessageResponseDTO message = MessageResponseDTO.builder()
                .message(MessagesConstants.TASK_DELETED_SUCCESSFULLY)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
        return ResponseEntity.ok(message);
    }
}
