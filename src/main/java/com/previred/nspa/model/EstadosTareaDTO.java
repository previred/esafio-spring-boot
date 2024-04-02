package com.previred.nspa.model;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * EstadosTareaDTO
 */

@JsonTypeName("EstadosTarea")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-01T19:02:29.794985500-03:00[America/Santiago]")
public class EstadosTareaDTO {

  private Integer idEstado;

  private String nombreEstado;

  public EstadosTareaDTO idEstado(Integer idEstado) {
    this.idEstado = idEstado;
    return this;
  }

  /**
   * Get idEstado
   * @return idEstado
  */
  
  @Schema(name = "idEstado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("idEstado")
  public Integer getIdEstado() {
    return idEstado;
  }

  public void setIdEstado(Integer idEstado) {
    this.idEstado = idEstado;
  }

  public EstadosTareaDTO nombreEstado(String nombreEstado) {
    this.nombreEstado = nombreEstado;
    return this;
  }

  /**
   * Get nombreEstado
   * @return nombreEstado
  */
  
  @Schema(name = "nombreEstado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nombreEstado")
  public String getNombreEstado() {
    return nombreEstado;
  }

  public void setNombreEstado(String nombreEstado) {
    this.nombreEstado = nombreEstado;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EstadosTareaDTO estadosTarea = (EstadosTareaDTO) o;
    return Objects.equals(this.idEstado, estadosTarea.idEstado) &&
        Objects.equals(this.nombreEstado, estadosTarea.nombreEstado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEstado, nombreEstado);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EstadosTareaDTO {\n");
    sb.append("    idEstado: ").append(toIndentedString(idEstado)).append("\n");
    sb.append("    nombreEstado: ").append(toIndentedString(nombreEstado)).append("\n");
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

