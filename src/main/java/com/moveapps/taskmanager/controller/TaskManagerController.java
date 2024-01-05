package com.moveapps.taskmanager.controller;

import com.moveapps.taskmanager.common.dto.TaskStateDTO;
import com.moveapps.taskmanager.mappers.TaskStateMapper;
import com.moveapps.taskmanager.service.TaskStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/task-manager")
@RestController
@RequiredArgsConstructor
public class TaskManagerController extends AbstractController{

    private final TaskStateService taskStateService;
    private final TaskStateMapper taskStateMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TaskStateDTO taskStateDTO) {
        var externalId = taskStateService.create(taskStateDTO);
        return ResponseEntity.created(this.getLocationResource(externalId)).build();
    }


    @GetMapping("/{externalId}")
    public ResponseEntity<TaskStateDTO> findByExternalId(@PathVariable String externalId) {
      return ResponseEntity.ok().body(
          taskStateMapper.toDto(taskStateService.findByExternalId(externalId)));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody TaskStateDTO taskStateDTO){
        taskStateService.update(taskStateDTO);
        return ResponseEntity.noContent().build() ;
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<Void> delete(@PathVariable String externalId){
        taskStateService.delete(externalId);
        return ResponseEntity.noContent().build();
    }


}
