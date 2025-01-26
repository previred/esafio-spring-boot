package cl.previred.task.taskpreviredback.controller;

import cl.previred.task.taskpreviredback.domain.Task;
import cl.previred.task.taskpreviredback.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
@PreAuthorize("hasRole('USER')")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask() throws Exception {
        return ResponseEntity.ok(taskService.getTask());
    }


    @PostMapping(path = "/add")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws Exception {
        Task newTask=taskService.addNewTask(task);
        return ResponseEntity.ok(newTask);
    }

    @DeleteMapping(path = "/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) throws Exception {
        return  ResponseEntity.ok("task deleted successfully");
    }


    @PutMapping(path = "/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) throws Exception {
        return ResponseEntity.ok(taskService.updateTask(task));
    }


}
