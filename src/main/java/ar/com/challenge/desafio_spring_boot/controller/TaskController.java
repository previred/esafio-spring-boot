package ar.com.challenge.desafio_spring_boot.controller;

import ar.com.challenge.desafio_spring_boot.request.TaskRequest;
import ar.com.challenge.desafio_spring_boot.response.*;
import ar.com.challenge.desafio_spring_boot.response.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@Tag(name = "Task", description = "Task API")
public class TaskController {

    @Operation(summary = "Create task")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Response<TaskResponse, Error> save(@RequestBody TaskRequest request) {

        var response = TaskResponse.builder().build();

        return Response.ok(response);
    }

    @Operation(summary = "Update task")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @PatchMapping()
    public Response<TaskResponse, Error> update(@RequestBody TaskRequest request) {

        var response = TaskResponse.builder().build();

        return Response.ok(response);
    }

    @Operation(summary = "Search task")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = TasksResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @GetMapping()
    public Response<TasksResponse, Error> getAll() {

        var response = TasksResponse.builder().build();

        return Response.ok(response);
    }

    @Operation(summary = "Search task by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @GetMapping("/{id}")
    public Response<TaskResponse, Error> getById(@Parameter(description = "Id de tarea", required = true)
                                                 @PathVariable(value = "id") int id) {

        var response = TaskResponse.builder().build();

        return Response.ok(response);
    }

    @Operation(summary = "Delete task by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(
                    schema = @Schema(implementation = TaskResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response<TaskResponse, Error> deleteById(@Parameter(description = "Id de tarea", required = true)
                                                    @PathVariable(value = "id") int id) {

        var response = TaskResponse.builder().build();

        return Response.ok(response);
    }
}
