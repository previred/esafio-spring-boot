package cl.previred.tasksapi.controller;

import cl.previred.tasksapi.service.TasksService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.TasksApi;
import org.openapitools.model.Task;
import org.openapitools.model.TaskInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks-api/api/v1/private/tasks")
@Slf4j
public class TasksController implements TasksApi {
    @Autowired
    private TasksService service;

    @DeleteMapping(value = "/{taskId}")
    @Override
    public ResponseEntity<Void> delTaskById(@PathVariable(value = "taskId") Integer taskId) {
        log.info("Eliminando tarea {}", taskId);

        service.deleteTask(taskId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("Todas las tareas");

        List<Task> result = service.getAllTasks();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{taskId}")
    @Override
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "taskId") Integer taskId) {
        log.info("Obtiene una tarea por id ({})", taskId);

        Task result = service.getTask(taskId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    @Override
    public ResponseEntity<Task> postNewTask(@Valid @RequestBody TaskInput taskInput) {
        log.info("Nueva tarea");

        Task result = service.newTask(taskInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(value = "/{taskId}")
    @Override
    public ResponseEntity<Task> putUpdateTaskById(@PathVariable(value = "taskId") Integer taskId, @Valid @RequestBody TaskInput taskInput) {
        log.info("Actualiza tarea");

        Task result = service.updateTask(taskInput, taskId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
