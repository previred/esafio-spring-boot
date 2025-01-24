package cl.previred.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.taskmanager.dto.TaskRequest;
import cl.previred.taskmanager.dto.TaskResponse;
import cl.previred.taskmanager.service.TaskService;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
		
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
    	return taskService.getAllTasks();
//        return taskRepository.findAll();
    }

	@GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id) {
		return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest task) {
    	return taskService.createTask(task);
        //return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
    	taskService.deleteTask(id);
    }
    
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest task) {
    	return taskService.updateTask(id, task);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hola, este recurso está protegido por JWT";
    }
    
}