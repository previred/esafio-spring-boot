package cl.previred.desafio.controller;

import cl.previred.desafio.dto.TaskRequestDTO;
import cl.previred.desafio.dto.TaskResponseDTO;
import cl.previred.desafio.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks/")
public class TaskController {

    @GetMapping
    public List<TaskResponseDTO> getTaskList(){
        log.info("GET /tasks/");
        List<TaskResponseDTO> response = new ArrayList<>();
        response.add(TaskResponseDTO.builder().description("a task").build());
        log.info(String.format("response: %d", response.size()));
        return response;
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO task){
        log.info("POST /tasks/ " + task);
        return TaskResponseDTO.builder().description(task.getDescription()).build();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable String id){
        log.info(String.format("GET /tasks/%s", id));
        return TaskResponseDTO.builder().id(UUID.fromString(id)).build();
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable String id, @RequestBody TaskRequestDTO task){
        log.info(String.format("PUT /tasks/%s", id));
        return TaskResponseDTO.builder().id(UUID.fromString(id)).description(task.getDescription()).build();
    }

    @DeleteMapping("/{id}")
    public TaskResponseDTO deleteTask(@PathVariable String id) {
        log.info(String.format("DELETE /tasks/%s", id));
        return TaskResponseDTO.builder().id(UUID.fromString(id)).build();
    }

}


