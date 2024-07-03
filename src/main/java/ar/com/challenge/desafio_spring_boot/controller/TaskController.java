package ar.com.challenge.desafio_spring_boot.controller;

import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;
import ar.com.challenge.desafio_spring_boot.request.TaskRequest;
import ar.com.challenge.desafio_spring_boot.response.*;
import ar.com.challenge.desafio_spring_boot.response.Error;
import ar.com.challenge.desafio_spring_boot.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Tag(name = "Task", description = "Task API")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Create task")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "406", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Response<TaskResponse, Error> save(@RequestBody TaskRequest request) throws ResourceFoundException, ResourceNotFoundException {

        var tasks = taskService.save(request.getTaskDto());

        var response = TaskResponse.builder().taskDto(tasks).build();

        return Response.ok(response);
    }

    @Operation(summary = "Update task")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "406", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @PatchMapping()
    public Response<TaskResponse, Error> update(@RequestBody TaskRequest request) throws ResourceNotFoundException {

        var tasks = taskService.update(request.getTaskDto());

        var response = TaskResponse.builder().taskDto(tasks).build();

        return Response.ok(response);
    }

    @Operation(summary = "Search task")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = TasksResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "406", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @GetMapping()
    public Response<TasksResponse, Error> getAll() {

        var tasks = taskService.getAll();

        var response = TasksResponse.builder().tasks(tasks).build();

        return Response.ok(response);
    }

    @Operation(summary = "Search task by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "406", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @GetMapping("/{id}")
    public Response<TaskResponse, Error> getById(@Parameter(description = "Id de tarea", required = true)
                                                 @PathVariable(value = "id") int id) throws ResourceNotFoundException {
        var tasks = taskService.getById(id);

        var response = TaskResponse.builder().taskDto(tasks).build();

        return Response.ok(response);
    }

    @Operation(summary = "Delete task by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "406", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response<TaskResponse, Error> deleteById(@Parameter(description = "Id de tarea", required = true)
                                                    @PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        taskService.delete(id);

        var response = TaskResponse.builder().build();

        return Response.ok(response);
    }
}
