package com.previred.desafiobackend.presentation.controller.task;

import com.previred.desafiobackend.domain.dto.enums.TaskStatusEnum;
import com.previred.desafiobackend.domain.dto.error.ApiError;
import com.previred.desafiobackend.domain.dto.task.request.CreateTask;
import com.previred.desafiobackend.domain.dto.task.response.GetTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    @PostMapping(value = "/")
    @Operation(description = "Creates a new task with the recieved parameters. All task are created with PENDING status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created."),
            @ApiResponse(responseCode = "500", description = "Task not created due to an service internal error.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value =
                                    "{\"message\": \"Cannot create new entity.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
            @ApiResponse(responseCode = "400", description = "Some information on the request is missing or not valid.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value =
                                    "{\"message\": \"The field asignedUserDni is mandatory.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
    })
    public ResponseEntity<Void> create(@RequestBody CreateTask createTask) {
        //TODO: task service:create method invocation.
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    @Operation(description = "Return all the task currently on the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks Found.", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetTask.class))}),
            @ApiResponse(responseCode = "500", description = "Task not found due to an service internal error.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value =
                                    "{\"message\": \"Internal Server Error.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
            @ApiResponse(responseCode = "404", description = "Zero tasks Not Found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value =
                                    "{\"message\": \"No tasks found.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
    })
    public ResponseEntity<Void> getaLL() {
        //TODO: task service:create method invocation.
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{task-id}")
    @Operation(description = "Performs a search and return the task associated with the received Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Found.", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetTask.class))}),
            @ApiResponse(responseCode = "500", description = "Task not found due to an service internal error.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value =
                                    "{\"message\": \"Internal Server Error.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
            @ApiResponse(responseCode = "404", description = "Task Not Found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value =
                                    "{\"message\": \"No task found for the received Id.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
    })
    public ResponseEntity<Void> get(@PathVariable (name = "task-id") Integer taskId) {
        //TODO: task service:create method invocation.
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{task-id}/{new-status}")
    @Operation(description = "Updates the status of the task associated with the received Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status updated."),
            @ApiResponse(responseCode = "500", description = "Status not updated due to an service internal error.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(name = "Internal Server Error",
                                    value = "{\"message\": \"Cannot create new entity.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
            @ApiResponse(responseCode = "400", description = "Task Id not found or status not allowed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(name = "Bad Request",
                                    value = "{\"message\": \"The new status is not allowed for the current status.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
    })
    public ResponseEntity<Void> update(@PathVariable (name = "task-id") Integer taskId,
                                       @PathVariable (name = "new-status") TaskStatusEnum newStatus) {
        //TODO: task service:create method invocation.
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{task-id}")
    @Operation(description = "Performs a delete for the entity associated with the received Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Deleted."),
            @ApiResponse(responseCode = "500", description = "Task not deleted due to an service internal error.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(name = "Internal Server Error",
                                    value = "{\"message\": \"Task not deleted due to an service internal error.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
            @ApiResponse(responseCode = "400", description = "Some information on the request is missing or not valid.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(name = "Bad Request",
                                    value = "{\"message\": \"No task found for the received Id.\", \"timestamp\": \"2024-02-09T12:00:00\"}")})}),
    })
    public ResponseEntity<Void> delete(@PathVariable (name = "task-id") Integer taskId) {
        //TODO: task service:create method invocation.
        return ResponseEntity.ok().build();
    }

}
