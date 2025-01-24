package cl.previred.gestion.api;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.gestion.dto.TaskRequest;
import cl.previred.gestion.dto.TaskResponse;
import cl.previred.gestion.model.Task;
import cl.previred.gestion.model.TaskStatus;
import cl.previred.gestion.model.User;
import cl.previred.gestion.service.TaskService;
import cl.previred.gestion.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Tareas Controller", description = "operaciones CRUD de tareas")
@RestController
@RequestMapping("/tareas")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }
    @Operation(summary = "Crear una nueva tarea", description = "Crea una nueva tarea asignada a un usuario.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarea creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content)
    })
    @PostMapping("/crearTarea")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest, @AuthenticationPrincipal UserDetails userDetails) {
       
        if (taskRequest.getStatus() == null || taskRequest.getDescription() == null) {
            return ResponseEntity.badRequest().body("Description y status son requeridos.");
        }

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado.");
        }

        User user = userService.findByUsername(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        }

        TaskStatus status = taskService.findStatusByName(taskRequest.getStatus());
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado invalido de la tarea.");
        }

        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setStatus(status);
        task.setUser(user);

        taskService.saveTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body("Tarea creada correctamente.");
    }

    @Operation(summary = "Actualizar una tarea existente", description = "Actualiza la descripción y el estado de una tarea existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada", content = @Content)
    })
@PutMapping("/actualizarTarea/{id}")
public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest, @AuthenticationPrincipal UserDetails userDetails) {

    if (userDetails == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado.");
    }

    Optional<Task> taskOptional = taskService.findTaskById(id);
    if (taskOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada con id: " + id);
    }
    Task task = taskOptional.get();

    if (!task.getUser().getUsername().equals(userDetails.getUsername())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para actualizar esta tarea.");
    }

    if (taskRequest.getDescription() != null) {
        task.setDescription(taskRequest.getDescription());
    }

    if (taskRequest.getStatus() != null) {
        TaskStatus status = taskService.findStatusByName(taskRequest.getStatus());
        task.setStatus(status);
    }

    taskService.saveTask(task);

    return ResponseEntity.ok("Tarea actualizada correctamente.");
}

    @Operation(summary = "Listar todas las tareas", description = "Retorna una lista de todas las tareas existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tareas listadas con éxito"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content)
    })
    @GetMapping("/obtenerTareas")
    public ResponseEntity<?> getAllTasks(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado.");
        }

        List<Task> tasks = taskService.findAllTasks();
        List<TaskResponse> taskResponses = tasks.stream()
        .map(task -> new TaskResponse(
            task.getId(),
            task.getDescription(),
            task.getStatus().getName(),
            task.getUser().getUsername(),
            task.getUser().getEmail()
        ))
        .collect(Collectors.toList());

        return ResponseEntity.ok(taskResponses);
    }


    @Operation(summary = "Obtener tarea por ID", description = "Devuelve una tarea específica según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada", content = @Content)
    })
    @GetMapping("/obtenerTarea/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado.");
        }

        //Task task = taskService.findTaskById(id);
        Optional<Task> taskOptional = taskService.findTaskById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada con id: " + id);
        }
        Task task = taskOptional.get();
        if (!task.getUser().getUsername().equals(userDetails.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para actualizar esta tarea.");
        }
    TaskResponse taskResponse = new TaskResponse(
        task.getId(),
        task.getDescription(),
        task.getStatus().getName(),
        task.getUser().getUsername(),
        task.getUser().getEmail()
    );
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Eliminar una tarea por ID", description = "Elimina una tarea específica por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea eliminada correctamente"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada", content = @Content)
    })
    @DeleteMapping("/eliminarTarea/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado.");
        }
        Optional<Task> taskOptional = taskService.findTaskById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada con id: " + id);
        }
        Task task = taskOptional.get();

        if (!task.getUser().getUsername().equals(userDetails.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para eliminar esta tarea.");
        }

        taskService.deleteTask(id);
        return ResponseEntity.ok("Tarea eliminada correctamente.");
    }
}
