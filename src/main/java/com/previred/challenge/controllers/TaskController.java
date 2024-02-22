package com.previred.challenge.controllers;

import com.previred.challenge.dto.JsonMessageDTO;
import com.previred.challenge.dto.PagedTaskResponseDTO;
import com.previred.challenge.dto.TaskRequestDTO;
import com.previred.challenge.dto.TaskResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

public interface TaskController {

    @PostMapping
    @Operation(description = "Create a task for logged user")
    TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO);

    @PostMapping("/bulk")
    @Operation(description = "Create a task for logged user in bulk")
    List<TaskResponseDTO> createTaskBulk(@RequestBody List<TaskRequestDTO> taskRequestDTOList);

    @GetMapping("/{taskId}")
    @Operation(description = "Retrieve a task by id for logged user")
    TaskResponseDTO getTaskById(@RequestParam Integer taskId);

    @PutMapping("/{taskId}")
    @Operation(description = "Update a task by id for logged user")
    TaskResponseDTO updateTask(@RequestParam Integer taskId, @RequestBody TaskRequestDTO taskRequestDTO);

    @DeleteMapping("/{taskId}")
    @Operation(description = "Remove a task. Only owners can delete it")
    JsonMessageDTO removeTask(@RequestParam Integer taskId);

    @GetMapping("/list")
    PagedTaskResponseDTO list(
            @RequestParam(defaultValue = "0", required = false) @Min(0) Integer page,
            @RequestParam(defaultValue = "20", required = false) @Max(20) Integer sizeInteger
    );

}
