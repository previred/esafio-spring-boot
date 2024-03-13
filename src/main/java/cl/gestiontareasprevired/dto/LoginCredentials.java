package cl.gestiontareasprevired.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.annotation.Generated;

/**
 * LoginCredentials
 */

@AllArgsConstructor
@Getter
@Setter
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-11T16:53:34.935907-03:00[America/Santiago]")
@Schema(description = "Ingreso credenciales login")
public class LoginCredentials {

  @NotNull(message = "El correo electrónico es obligatorio.")
  @Email(message = "Formato de correo electrónico inválido.")
  @Schema(description = "correo identificador", example = "usuario1@example.com")
  private String mail;

  @NotNull(message = "La contraseña es obligatoria.")
  @Pattern(regexp = "^[A-Za-z]{4}[0-9]{4}$", message = "La contraseña debe tener 4 letras seguidas de 4 números.")
  @Schema(description = "Contraseña", example = "pass1234")
  private String pass;


}

