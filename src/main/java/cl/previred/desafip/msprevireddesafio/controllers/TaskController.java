package cl.previred.desafip.msprevireddesafio.controllers;

import cl.previred.desafip.msprevireddesafio.entities.Task;
import cl.previred.desafip.msprevireddesafio.services.TaskService;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.getTaskById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada para el ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la tarea para el ID: " + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addTask(task));

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateTask(task));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener la tarea para el ID: " + task.getId());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {

        try {
            service.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Tarea eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener la tarea para el ID: " + id);
        }
    }




}
