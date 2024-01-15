package com.nuevoapp.prueba.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.nuevoapp.prueba.config.error.dto.CustomError;
import com.nuevoapp.prueba.domain.model.dto.PatchOperationExampleDto;
import com.nuevoapp.prueba.domain.model.dto.TasksDto;
import com.nuevoapp.prueba.domain.service.TasksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@Slf4j
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class TasksController {

    private final TasksService tasksService;

    @Operation(
            summary = "Obtains a single entity based on the id",
            description = "Obtains a single entity based on the id",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Bearer Authentication")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entity found",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksDto.class)
                            )),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No entity Found"
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<TasksDto> getTaskById(@PathVariable Integer id) {
        log.info("init call to getTaskById [GET] id: {}", id);
        return ResponseEntity.ok(tasksService.getTaskById(id));
    }

    @Operation(
            summary = "Obtains a list entities based on the provided email",
            description = "Obtains a list entities based on the provided email",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Authorization")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entities found",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TasksDto.class))
                            )),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No entity Found"
                    )
            })
    @GetMapping("/user/{email}")
    public ResponseEntity<List<TasksDto>> getTasksByEmail(@PathVariable String email) {
        log.info("init call to getTasksByEmail [GET] Email: {}", email);
        return ResponseEntity.ok(tasksService.getTaskByEmail(email));
    }

    @Operation(
            summary = "Creates a Task based on a given dto",
            description = "Creates a Task based on a given dto",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Authorization")},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Entity created",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksDto.class)
                            )),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomError.class)
                            ))
            })
    @PostMapping()
    public ResponseEntity<TasksDto> createTask(@RequestBody @Valid TasksDto dto) {
        log.info("init call to createTask [POST] dto.user.email: {}", dto.getUser().getEmail());
        return new ResponseEntity<>(tasksService.createTask(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Creates multiple entities based on the given DTO list",
            description = "Creates multiple entities based on the given DTO list",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Authorization")},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Entities Created",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TasksDto.class))
                            )),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomError.class)
                            ))
            })
    @PostMapping("/batch")
    public ResponseEntity<List<TasksDto>> createTaskBatch(@RequestBody @Valid List<TasksDto> list) {
        log.info("init call to createTaskBatch [POST] list.size: {}", list.size());
        return new ResponseEntity<>(tasksService.createTaskBatch(list), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Updates a Task, uses the whole entity",
            description = "Updates a Task, uses the whole entity",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Authorization")},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Entity updated",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksDto.class)
                            )),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No entity Found"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomError.class)
                            ))
            })
    @PutMapping()
    public ResponseEntity<TasksDto> updateTaskById(@RequestBody @Valid TasksDto dto) {
        log.info("init call to updateTaskById [PUT] dto.id: {}", dto.getId());
        return new ResponseEntity<>(tasksService.updateTaskById(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Updates a Task, must specify the action",
            description = "Updates a Task, must specify the action (“add”, “remove”, “replace”, “move”, “copy” and “test”)",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Authorization")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = PatchOperationExampleDto.class))
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entity updated",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksDto.class)
                            )),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No entity Found"
                            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomError.class)
                            ))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<TasksDto> patchTaskById(@PathVariable Integer id, @RequestBody JsonPatch patchOperations) {
        log.info("init call to updateTaskById [PATCH] id: {}", id);
        return ResponseEntity.ok(tasksService.patchTaskById(id, patchOperations));
    }

    @Operation(
            summary = "Deletes a task, given the id",
            description = "Deletes a task, given the id",
            tags = {"Task-Management"},
            security = {@SecurityRequirement(name = "Authorization")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entity deleted",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "boolean")
                            )),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No entity Found"
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTaskById(@PathVariable Integer id) {
        log.info("init call to updateTaskById [DELETE]id: {}", id);
        return ResponseEntity.ok(tasksService.deleteTaskById(id));
    }
}
