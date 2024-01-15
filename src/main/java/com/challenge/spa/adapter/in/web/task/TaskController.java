package com.challenge.spa.adapter.in.web.task;

import com.challenge.spa.adapter.in.web.task.resource.TaskRequest;
import com.challenge.spa.adapter.in.web.task.resource.TaskResponse;
import com.challenge.spa.application.port.in.ProcessTaskPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

  private final ProcessTaskPort processTaskPort;

  public TaskController(ProcessTaskPort processTaskPort) {
    this.processTaskPort = processTaskPort;
  }

  @GetMapping("/task")
  public ResponseEntity<List<TaskResponse>> index() {
    return ResponseEntity.ok(processTaskPort
            .all()
            .stream()
            .map(TaskResponse::fromDomain)
            .collect(Collectors.toList())
    );
  }

  @GetMapping("/task/{id}")
  public ResponseEntity<TaskResponse> find(@PathVariable String id) {
    return ResponseEntity.ok(TaskResponse
            .fromDomain(processTaskPort.load(id))
    );
  }

  @PostMapping("/task")
  public ResponseEntity<Void> save(@RequestBody TaskRequest taskRequest) {
    processTaskPort.save(taskRequest.toDomain());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/task/{id}")
  public ResponseEntity<Void> update(@PathVariable String id, @RequestBody TaskRequest taskRequest) {
    processTaskPort.update(id, taskRequest.toDomain());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/task/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    processTaskPort.delete(id);
    return ResponseEntity.noContent().build();
  }

}
