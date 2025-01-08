package co.api.gestiontareas.infrastructure.entry_points.controllers.tack;


import co.api.gestiontareas.domain.model.common.MensajeDTO;
import co.api.gestiontareas.domain.model.task.Task;
import co.api.gestiontareas.domain.service.TaskService;
import co.api.gestiontareas.infrastructure.entry_points.controllers.tack.request.CreateTaskRequest;
import co.api.gestiontareas.infrastructure.entry_points.controllers.tack.request.UpdateTaskRequest;
import co.api.gestiontareas.infrastructure.helpers.jwt.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/task")
public class TaskController {

    private  final TaskService taskService;
    private  final JWTUtil jwtUtil;

    @Operation(summary = "Crear una nueva tarea", description = "", security = {
            @SecurityRequirement(name = "BearerAuth")    }, tags={ "Task" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class))),

            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, parámetros faltantes o incorrectos"),

            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token JWT"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/save",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Task> createTask(@Parameter(in = ParameterIn.DEFAULT,
            description = "", required=true, schema=@Schema())
                                    @Valid @RequestBody CreateTaskRequest body
                                    ,@RequestHeader("Authorization") String token
    ){
        Integer id = (Integer)jwtUtil.parseJwt(
                token.replace("Bearer ", "")).getPayload().get("id");
        return ResponseEntity.ok().body(
                taskService.saveTask(
                        Task.builder()
                                .userId(id.longValue())
                                .description(body.description())
                                .title(body.title())
                        .build())
        );
    }


    @Operation(summary = "Eliminar una tarea por ID", description = "", security = {
            @SecurityRequirement(name = "BearerAuth")    }, tags={ "Task" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarea eliminada correctamente"),

            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token JWT"),

            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteTask(@Parameter(in = ParameterIn.PATH,
            description = "ID de la tarea a eliminar", required=true, schema=@Schema())
                                    @PathVariable("id") Long id
    ) throws Exception {
        taskService.deleteTask(id);
        return ResponseEntity.ok().body(
                new MensajeDTO<>(
                "Tarea eliminada correctamente"));
    }


    @Operation(summary = "Obtener todas las tareas", description = "", security = {
            @SecurityRequirement(name = "BearerAuth")    }, tags={ "Task" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tareas",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Task.class)))),

            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token JWT"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/all",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok().body(taskService.getTasks());
    }


    @Operation(summary = "Obtener una tarea por ID", description = "", security = {
            @SecurityRequirement(name = "BearerAuth")    }, tags={ "Task" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de la tarea",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Task.class))),

            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token JWT"),

            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Task> getTaskById(@Parameter(in = ParameterIn.PATH,
            description = "ID de la tarea a buscar", required=true, schema=@Schema())
                                     @PathVariable("id") Long id
    ){
        return ResponseEntity.ok().body(
                taskService.getTask(id)
        );
    }


    @Operation(summary = "Actualizar una tarea existente", description = "", security = {
            @SecurityRequirement(name = "BearerAuth")    }, tags={ "Task" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class))),

            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, parámetros faltantes o incorrectos"),

            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token JWT"),

            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Object> updateTask(@Parameter(in = ParameterIn.PATH, description = "ID de la tarea a actualizar",
            required=true, schema=@Schema()) @PathVariable("id") Long id
            , @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
                                    @Valid @RequestBody UpdateTaskRequest body
    ) throws Exception {

        taskService.updateTask(
            Task.builder()
                    .id(id)
                    .title(body.title())
                    .description(body.title())
                    .build());

        return ResponseEntity.ok().body(
                new MensajeDTO<>(
                "Tarea actualizada con éxito")
                );
    }
}
