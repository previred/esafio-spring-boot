package com.spa.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO Roles")
public class RolesDTO {
    @Schema(description = "Id del Rol", example = "1")
    private Long idRoles;
    @Schema(description = "Nombre de rol", example = "ADMIN")
    private String nombre;
}
