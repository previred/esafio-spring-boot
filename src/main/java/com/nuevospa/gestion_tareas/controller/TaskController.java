package com.nuevospa.gestion_tareas.controller;



import com.nuevospa.gestion_tareas.entity.Task;
import com.nuevospa.gestion_tareas.model.ApiResponseDetail;
import com.nuevospa.gestion_tareas.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "TaskController", description = "Controlador para la gestión de tareas. Permite crear, listar, actualizar y eliminar tareas.")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(
            summary = "Listar todas las tareas",
            description = "Este endpoint permite obtener una lista de todas las tareas existentes. Si no se encuentran tareas, se devuelve un mensaje indicando que no hay contenido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tareas recuperadas exitosamente",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponseDetail.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron tareas"),
    })
    @GetMapping
    public ResponseEntity<ApiResponseDetail<List<Task>>> listarTareas() {
        List<Task> tareas = taskService.findAll();
        if (tareas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseDetail<>("No se encontraron tareas", null, false, HttpStatus.NO_CONTENT.value()));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseDetail<>("Tareas recuperadas exitosamente", tareas, true, HttpStatus.OK.value()));
        }
    }


    @Operation(
            summary = "Obtener tarea por ID",
            description = "Este endpoint permite obtener los detalles de una tarea existente utilizando su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea recuperada exitosamente",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponseDetail.class))),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada con el ID proporcionado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDetail<Task>> obtenerTareaPorId(
            @Parameter(description = "ID de la tarea que se va a recuperar") @PathVariable Long id
    ) {
        return taskService.findById(id)
                .map(tarea ->
                        ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponseDetail<>("Tarea recuperada exitosamente", tarea, true, HttpStatus.OK.value()))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponseDetail<>("Tarea no encontrada con ID: " + id, null, false, HttpStatus.NOT_FOUND.value()))
                );
    }

    @Operation(summary = "Crear una nueva tarea",
            description = "Este endpoint permite crear una nueva tarea en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarea creada correctamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            })
    @PostMapping
    public ResponseEntity<ApiResponseDetail<Task>> crearTarea(@RequestBody Task tarea) {
        try {
            Task nuevaTarea = taskService.save(tarea);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseDetail<>("Tarea creada exitosamente", nuevaTarea, true, HttpStatus.CREATED.value()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDetail<>("Se produjo un error al crear la tarea.: " + ex.getMessage(), null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }


    @Operation(
            summary = "Actualizar tarea",
            description = "Este endpoint permite actualizar los detalles de una tarea existente utilizando el ID de la tarea."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponseDetail.class))),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada con el ID proporcionado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDetail<Task>> actualizarTarea(
            @Parameter(description = "ID de la tarea que se va a actualizar") @PathVariable Long id,
            @Parameter(description = "Datos de la tarea a actualizar") @RequestBody Task tarea
    ) {
        return taskService.findById(id)
                .map(t -> {
                    // Actualizar los campos de la tarea existente
                    t.setTitulo(tarea.getTitulo());
                    t.setDescripcion(tarea.getDescripcion());
                    t.setEstado(tarea.getEstado());
                    t.setUsuario(tarea.getUsuario());
                    Task updatedTask = taskService.save(t);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ApiResponseDetail<>("Tarea actualizada exitosamente", updatedTask, true, HttpStatus.OK.value()));
                })
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponseDetail<>("Tarea no encontrada con id: " + id, null, false, HttpStatus.NOT_FOUND.value()))
                );
    }

    @Operation(
            summary = "Eliminar tarea",
            description = "Este endpoint elimina una tarea del sistema según el ID proporcionado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarea eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada con el ID proporcionado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDetail<String>> eliminarTarea(
            @Parameter(description = "ID de la tarea que se va a eliminar") @PathVariable Long id) {
        if (taskService.existsById(id)) {
            taskService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseDetail<>("Tarea eliminada exitosamente", null, true, HttpStatus.NO_CONTENT.value()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDetail<>("Tarea no encontrada con id: " + id, null, false, HttpStatus.NOT_FOUND.value()));
        }
    }

}
