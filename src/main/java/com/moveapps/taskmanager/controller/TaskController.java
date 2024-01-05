package com.moveapps.taskmanager.controller;

import com.moveapps.taskmanager.common.dto.TaskDTO;
import com.moveapps.taskmanager.mappers.TaskMapper;
import com.moveapps.taskmanager.service.TaskService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tasks")
@RestController
@AllArgsConstructor
public class TaskController extends AbstractController{

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    @GetMapping("/{externalId}")
    public ResponseEntity<TaskDTO> findByExternalId(@PathVariable("externalId") String externalId){
        return ResponseEntity.ok(taskMapper.toDto(taskService.findByExternalId(externalId)));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll(){
        return ResponseEntity.ok(taskService.findAll()
            .stream()
            .map(taskMapper::toDto)
            .toList());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TaskDTO taskDTO){
        var externalId = taskService.create(taskDTO);
        return ResponseEntity.created(getLocationResource(externalId)).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody TaskDTO taskDTO){
        taskService.update(taskDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<Void> delete(@PathVariable("externalId") String externalId){
        taskService.delete(externalId);
        return ResponseEntity.noContent().build();
    }
}
