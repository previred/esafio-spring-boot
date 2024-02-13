package cl.nuevospa.taskmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud para crear o actualizar una tarea.")
public class TaskRequestDTO {

    @NotBlank(message = "El título de la tarea es requerido")
    @Size(max = 255, message = "El título de la tarea no debe exceder los 255 caracteres")
    @Schema(description = "Título de la tarea.", example = "Finalizar documentación del proyecto")
    private String title;

    @NotBlank(message = "La descripción de la tarea es requerida")
    @Size(max = 500, message = "La descripción de la tarea no debe exceder los 500 caracteres")
    @Schema(description = "Descripción detallada de la tarea.", example = "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.")
    private String description;

    @NotNull(message = "El estado de la tarea es requerido")
    @Pattern(regexp = "PENDIENTE|EN_PROCESO|COMPLETADO", message = "El estado de la tarea debe ser uno de los siguientes: PENDIENTE, EN_PROCESO, COMPLETADO")
    @Schema(description = "Estado actual de la tarea.", example = "PENDIENTE")
    private String state;

    @NotBlank(message = "El nombre de usuario es requerido")
    @NotNull(message = "El nombre de usuario es requerido")
    @Schema(description = "Nombre de usuario del propietario de la tarea.", example = "usuarioEjemplo")
    private String username;
}
