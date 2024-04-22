package com.company.api.task;

import com.company.model.Task;
import com.company.service.task.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController implements  ITaskController{

    private final TaskService taskService;

    @Override
    public void create(@RequestBody final Task task) {
        taskService.create(task);
    }

    @Override
    public void update(@RequestBody final Task task) {
        taskService.update(task);
    }

    @Override
    public void delete(@RequestBody final UUID uuid) {
        taskService.delete(uuid);
    }

    @Override
    public ResponseEntity<Optional<Task>> findById(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(taskService.findById(uuid));
    }

    @Override
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }
}
