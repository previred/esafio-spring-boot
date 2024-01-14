package jlillor.ms.tasks.manager.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jlillor.ms.tasks.manager.dtos.MsResponse;
import jlillor.ms.tasks.manager.dtos.NewTaskRequest;
import jlillor.ms.tasks.manager.dtos.TaskResponse;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * The Class TaskManagementController.
 * 
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskManagementController {
    
    /** The service. */
    private final TaskService service;
    /** The message property. */ 
    private final MessageProperty messageProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos Públicos ---------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Método que crea una nueva tarea.
     *
     * @param createTask {@link NewTaskRequest} la tarea a crear.
     * @param token {@link String} el token del usuario.
     * @return la tarea creada.
     */
    @ApiOperation("Método que crea una nueva tarea.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @PostMapping
    public ResponseEntity<MsResponse<TaskResponse>> create(
            @RequestHeader("token") final String token,
            @Valid @RequestBody final NewTaskRequest createTask) {
        final var respuesta = new MsResponse<>(messageProperty.getSuccess(), service.create(createTask, token));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }

    /**
     * Método que edita una tarea.
     *
     * @param createTask {@link NewTaskRequest} la tarea a editar.
     * @param token {@link String} el token del usuario.
     * @param id {@link Long} el id de la tarea.
     * @return la tarea editada.
     */
    @ApiOperation("Método que edita una tarea.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @PutMapping("/{id}")
    public ResponseEntity<MsResponse<TaskResponse>> edit(
            @RequestHeader("token") final String token,
            @PathVariable(value = "id") final Long id,
            @Valid @RequestBody final NewTaskRequest createTask) {
        final var respuesta = new MsResponse<>(messageProperty.getSuccess(), service.edit(createTask, token, id));
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    /**
     * Método que elimina una tarea.
     *
     * @param token {@link String} el token del usuario.
     * @param id {@link Long} el id de la tarea.
     * @return Mensaje de éxito o error.
     */
    @ApiOperation("Método que elimina una tarea.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<MsResponse<TaskResponse>> delete(
            @RequestHeader("token") final String token,
            @PathVariable(value = "id") final Long id) {
        service.delete(token, id);
        return ResponseEntity.status(HttpStatus.OK).body(new MsResponse<>(messageProperty.getSuccess()));

    }
    
    /**
     * Método que obtiene todas las tareas.
     *
     * @param token el token del usuario.
     * @return la lista de tareas.
     */
    @ApiOperation("Método que obtiene todas las tareas.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping
    public ResponseEntity<MsResponse<List<TaskResponse>>> getAll(
            @RequestHeader("token") final String token) {
        final var respuesta = new MsResponse<>(messageProperty.getSuccess(), service.getAll(token));
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
