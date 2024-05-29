package cl.previred.desafio.controller;

import cl.previred.desafio.dto.StateTaskDTO;
import cl.previred.desafio.dto.TaskRequestDTO;
import cl.previred.desafio.dto.TaskResponseDTO;
import cl.previred.desafio.dto.UserResponseDTO;
import cl.previred.desafio.entity.StateTaskEntity;
import cl.previred.desafio.entity.TaskEntity;
import cl.previred.desafio.entity.UserEntity;
import cl.previred.desafio.service.TaskService;
import cl.previred.desafio.service.UserService;
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

    private final TaskService taskService;

    private final UserService userService;

    @GetMapping
    public List<TaskResponseDTO> getTaskList(){
        log.info("GET /tasks/");
        List<TaskResponseDTO> response = new ArrayList<>();
        for(TaskEntity task : taskService.findAll()){
            response.add(taskEntity2DTO(task));
        }
        log.info(String.format("response: %d", response.size()));
        return response;
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO task){
        log.info("POST /tasks/ " + task);
        return taskEntity2DTO(taskService.createTask(taskDTO2Entity(task)));
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

    /**
     * Transformations
     *
     */
    private TaskResponseDTO taskEntity2DTO(TaskEntity task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .user(userEntity2DTO(task.getUser()))
                .status(statusEntity2DTO(task.getStatus()))
                .description(task.getDescription())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    private UserResponseDTO userEntity2DTO(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    private StateTaskDTO statusEntity2DTO(StateTaskEntity status) {
        return StateTaskDTO.builder()
                .status(status.getName())
                .id(status.getId())
                .build();
    }


    private TaskEntity taskDTO2Entity(TaskRequestDTO task) {
        return TaskEntity.builder()
                .user(userService.findUserById(task.getIdUser()))
                .description(task.getDescription())
                .build();
    }


}
