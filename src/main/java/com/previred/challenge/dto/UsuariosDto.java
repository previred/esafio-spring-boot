package com.previred.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto de Usuarios")
public class UsuariosDto {
	@Schema(description = "Nombre de usuario", example = "Sergio")
	private String name;
	@Schema(description = "Apellido de usuario", example = "Silva")
	private String lastname;
	@Schema(description = "Nombre de cuenta", example = "sergioadmin")
	private String username;
	@Schema(description = "Email de usuario", example = "sergio@mail.com")
	private String mail;
	@Schema(description = "Rol de usuario", example = "ADMIN")
	private String role;
	@Schema(description = "Token de usuario", example = "eyJhbGciOiJIUzI1NiJ9")
	private String token;
}
