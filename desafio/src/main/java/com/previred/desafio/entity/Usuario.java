package com.previred.desafio.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa a un usuario en el sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1", required = true)
    private Long id;

    @Schema(description = "Nombre de usuario para iniciar sesión", example = "johndoe", required = true)
    private String username;

    @Schema(description = "Contraseña del usuario", example = "1234", required = true)
    private String password;

}
