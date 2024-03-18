package com.nuevospa.taskmanager.ports.input;

import com.nuevospa.taskmanager.application.TareasService;
import com.nuevospa.taskmanager.domain.model.Tareas;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
@Api(tags = "Tareas Controller", description = "Operaciones relacionadas con las tareas.")
public class TareasController {

    @Autowired
    private TareasService taskService;

    @GetMapping
    public ResponseEntity<List<Tareas>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tareas> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tareas> createTask(@RequestBody Tareas task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tareas> updateTask(@PathVariable Long id, @RequestBody Tareas task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
