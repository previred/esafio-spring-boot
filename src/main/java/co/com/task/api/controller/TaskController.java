package co.com.task.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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

import co.com.task.api.dto.ErrorResponseDTO;
import co.com.task.api.dto.TaskDTO;
import co.com.task.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
	this.taskService = taskService;
    }

    @Operation(summary = "Consulta todas las tareas")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "OK", content = {
		    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class))) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll() {
	return ResponseEntity.ok(taskService.getAll());
    }

    @Operation(summary = "Consulta una tarea por ID")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Ok", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @GetMapping("/task-id/{idTask}")
    public ResponseEntity<TaskDTO> getById(@PathVariable UUID idTask) {
	return ResponseEntity.ok(taskService.getById(idTask));
    }

    @Operation(summary = "Realiza la creacion de una tarea")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @PostMapping("/save")
    public ResponseEntity<TaskDTO> save(@RequestBody @Valid TaskDTO task) {
	return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @Operation(summary = "Realiza la actualizacion de una tarea")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Ok", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @PutMapping("/update")
    public ResponseEntity<TaskDTO> update(@RequestBody @Valid TaskDTO task) {
	return new ResponseEntity<>(taskService.update(task), HttpStatus.OK);
    }

    @Operation(summary = "Elimina una tarea por ID")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Ok", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @DeleteMapping("/delete/{idTask}")
    public ResponseEntity<Void> delete(@PathVariable UUID idTask) {
	taskService.delete(idTask);
	return ResponseEntity.ok().build();
    }

}
