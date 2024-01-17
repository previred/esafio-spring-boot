package cl.previred.challenge.dto.request;

import javax.validation.constraints.NotNull;

public record ChangeTaskStatusRequest(
        @NotNull(message = "Task status required") String status) {
}
