package com.previred.nspa.model;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * UsuariosDTO
 */

@JsonTypeName("Usuarios")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-01T19:02:29.794985500-03:00[America/Santiago]")
public class UsuariosDTO {

  private Integer idUsuario;

  private String nombreUsuario;

  private String correoElectronico;

  private String contrasenaHash;

  public UsuariosDTO idUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

  /**
   * Get idUsuario
   * @return idUsuario
  */
  
  @Schema(name = "idUsuario", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("idUsuario")
  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public UsuariosDTO nombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
    return this;
  }

  /**
   * Get nombreUsuario
   * @return nombreUsuario
  */
  
  @Schema(name = "nombreUsuario", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nombreUsuario")
  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public UsuariosDTO correoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
    return this;
  }

  /**
   * Get correoElectronico
   * @return correoElectronico
  */
  
  @Schema(name = "correoElectronico", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("correoElectronico")
  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public UsuariosDTO contrasenaHash(String contrasenaHash) {
    this.contrasenaHash = contrasenaHash;
    return this;
  }

  /**
   * Get contrasenaHash
   * @return contrasenaHash
  */
  
  @Schema(name = "contrasenaHash", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contrasenaHash")
  public String getContrasenaHash() {
    return contrasenaHash;
  }

  public void setContrasenaHash(String contrasenaHash) {
    this.contrasenaHash = contrasenaHash;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsuariosDTO usuarios = (UsuariosDTO) o;
    return Objects.equals(this.idUsuario, usuarios.idUsuario) &&
        Objects.equals(this.nombreUsuario, usuarios.nombreUsuario) &&
        Objects.equals(this.correoElectronico, usuarios.correoElectronico) &&
        Objects.equals(this.contrasenaHash, usuarios.contrasenaHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUsuario, nombreUsuario, correoElectronico, contrasenaHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuariosDTO {\n");
    sb.append("    idUsuario: ").append(toIndentedString(idUsuario)).append("\n");
    sb.append("    nombreUsuario: ").append(toIndentedString(nombreUsuario)).append("\n");
    sb.append("    correoElectronico: ").append(toIndentedString(correoElectronico)).append("\n");
    sb.append("    contrasenaHash: ").append(toIndentedString(contrasenaHash)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

