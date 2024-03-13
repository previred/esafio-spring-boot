package cl.gestiontareasprevired.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * LoginSuccessResponse
 */

@AllArgsConstructor
@Getter
@Setter
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-11T16:53:34.935907-03:00[America/Santiago]")
@Schema(description = "Respuesta de éxito de inicio de sesión")
public class LoginSuccessResponse {

  @Schema(description = "Indica si el inicio de sesión fue exitoso", example = "true")
  private Boolean success;

  @Schema(description = "token generado", example = "hcmlvMUasa")
  private String token;

  @Schema(description = "Mensaje de éxito", example = "Autenticación exitosa")
  private String message;

}

