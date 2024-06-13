package com.example.usertask.web;


import com.example.usertask.domain.Task;
import com.example.usertask.domain.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    public Iterable<Task> getCars() {
        return repository.findAll();
    }
}
