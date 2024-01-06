package dev.rhc.apiuser.controller;

import dev.rhc.apiuser.dto.TaskDto;
import dev.rhc.apiuser.model.Task;
import dev.rhc.apiuser.service.TaskService;
import dev.rhc.apiuser.util.DtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final DtoConverter dtoConverter;

    @GetMapping("/task")
    public ResponseEntity<List<TaskDto>> findAll() {
        List<Task> listTask = taskService.findAll();
        return new ResponseEntity<>(dtoConverter.convertTasksToDto(listTask), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(dtoConverter.convertTaskToDto(task));
    }


    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = dtoConverter.convertDtoToTask(taskDto);
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(dtoConverter.convertTaskToDto(createdTask), HttpStatus.CREATED);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = dtoConverter.convertDtoToTask(taskDto);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(dtoConverter.convertTaskToDto(updatedTask));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
