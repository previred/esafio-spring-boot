package cl.previred.challenge.dto.request;

import javax.validation.constraints.NotNull;

public record AssignTaskToRequest(
        @NotNull(message = "User email required") String userEmail) {
}
