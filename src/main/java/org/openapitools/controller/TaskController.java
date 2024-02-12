package org.openapitools.controller;

import io.swagger.annotations.ApiOperation;
import org.openapitools.dto.Task;
import org.openapitools.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ApiOperation("Servicio para obtener todas las tareas")
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/task-id/{taskId}")
    @ApiOperation("Servicio para obtener una tarea por el ID de la tarea")
    public ResponseEntity<Task> getById(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.getById(taskId));
    }

    @PostMapping("/save")
    @ApiOperation("Servicio para guardar y actualizar tareas")
    public ResponseEntity<Task> save(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{taskId}")
    @ApiOperation("Servicio para eliminar una tarea mediante el ID de la tarea")
    public ResponseEntity<Void> delete(@PathVariable UUID taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }
}
