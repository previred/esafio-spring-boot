package com.desafio.spring.ec.ws.expose.impl;

import com.desafio.spring.ec.bs.services.TaskServiceImpl;
import com.desafio.spring.ec.ds.dto.TasksTO;
import com.desafio.spring.ec.ws.expose.ITaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController implements ITaskController {

    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TasksTO> createTask(TasksTO task) throws Exception {
        return ResponseEntity.ok(taskService.save(task));
    }

    @Override
    public ResponseEntity<TasksTO> updateTask(TasksTO task, Long id) throws Exception {
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @Override
    public ResponseEntity<TasksTO> getTask(Long id) throws Exception {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @Override
    public ResponseEntity<List<TasksTO>> getAllTasks() throws Exception {
        return ResponseEntity.ok(taskService.findAll());
    }

    @Override
    public ResponseEntity deleteTask(Long id) throws Exception {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

}
