package com.challenge.spa.adapter.in.web.task;

import com.challenge.spa.adapter.in.web.task.resource.TaskRequest;
import com.challenge.spa.adapter.in.web.task.resource.TaskResponse;
import com.challenge.spa.application.port.in.ProcessTaskPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Task", description = "Task management APIs")
public class TaskController {

  private final ProcessTaskPort processTaskPort;

  public TaskController(ProcessTaskPort processTaskPort) {
    this.processTaskPort = processTaskPort;
  }

  @Operation(
          summary = "Retrieve a all Tasks",
          description = "Get a all task objects. " +
                  "The response is list of task object with id, name, description and status.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = { @Content
                  (array = @ArraySchema(schema = @Schema(implementation = TaskResponse.class))
                          , mediaType = "application/json") }),
          @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @GetMapping("/task")
  public ResponseEntity<List<TaskResponse>> index() {
    return ResponseEntity.ok(processTaskPort
            .all()
            .stream()
            .map(TaskResponse::fromDomain)
            .collect(Collectors.toList())
    );
  }
  @Operation(
          summary = "Retrieve a Task by Id",
          description = "Get a task object by specifying its id. " +
                  "The response is task object with id, name, description and status.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskResponse.class), mediaType = "application/json") }),
          @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
          @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @GetMapping("/task/{id}")
  public ResponseEntity<TaskResponse> find(@PathVariable String id) {
    return ResponseEntity.ok(TaskResponse
            .fromDomain(processTaskPort.load(id))
    );
  }

  @Operation(summary = "Create a new Task")
  @ApiResponses({
          @ApiResponse(responseCode = "201", content = {
                  @Content(schema = @Schema()) }),
          @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @PostMapping("/task")
  public ResponseEntity<Void> save(@RequestBody TaskRequest taskRequest) {
    processTaskPort.save(taskRequest.toDomain());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "Update a Task by id")
  @ApiResponses({
          @ApiResponse(responseCode = "204", content = {
                  @Content(schema = @Schema()) }),
          @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @PatchMapping("/task/{id}")
  public ResponseEntity<Void> update(@PathVariable String id, @RequestBody TaskRequest taskRequest) {
    processTaskPort.update(id, taskRequest.toDomain());
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Delete a Task by id")
  @ApiResponses({
          @ApiResponse(responseCode = "204", content = {
                  @Content(schema = @Schema()) }),
          @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @DeleteMapping("/task/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    processTaskPort.delete(id);
    return ResponseEntity.noContent().build();
  }

}
