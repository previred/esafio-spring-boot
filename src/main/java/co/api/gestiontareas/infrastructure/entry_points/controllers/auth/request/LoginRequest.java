package co.api.gestiontareas.infrastructure.entry_points.controllers.auth.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Debe ingresar un username")
        String username,
        @NotBlank(message = "Debe ingresar un password")
        String password
) {
}
