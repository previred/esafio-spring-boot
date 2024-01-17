package cl.previred.challenge.dto;

import cl.previred.challenge.repository.entity.Task;

import java.util.Objects;

public record TaskDto(
        Integer id,
        String name,
        String description,
        String createdDate,
        String createdBy,
        String assignedTo,
        String status) {

    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getDescription(),
                task.getCreatedDate().toString(),
                task.getCreatedBy().getEmail(),
                Objects.nonNull(task.getAssignedTo()) ? task.getAssignedTo().getEmail() : "",
                task.getStatus().getCode());
    }
}
