package cl.nuevospa.taskmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la respuesta de las tareas, incluyendo detalles como el ID único, título, descripción, fecha de creación, estado actual de la tarea y el usuario asignado.")
public class TaskResponseDTO {

    @Schema(description = "ID único de la tarea.", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Título de la tarea.", example = "Finalizar documentación del proyecto")
    private String title;

    @Schema(description = "Descripción detallada de la tarea.", example = "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.")
    private String description;

    @Schema(description = "Fecha y hora de creación de la tarea.", example = "2024-02-13T10:00:00")
    private LocalDateTime creationDate;

    @Schema(description = "Estado actual de la tarea.", example = "Pendiente")
    private String state;

    @Schema(description = "Nombre de usuario del propietario de la tarea.", example = "usuarioEjemplo")
    private String user;
}
