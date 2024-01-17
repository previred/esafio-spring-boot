package cl.previred.challenge.dto.request;

import javax.validation.constraints.NotNull;

public record NewTaskRequest(
        @NotNull(message = "Task name required") String name,
        String description,
        String assignedToEmail) {
}
