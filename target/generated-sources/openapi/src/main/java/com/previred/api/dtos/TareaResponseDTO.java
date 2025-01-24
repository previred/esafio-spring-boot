package com.previred.api.dtos;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.previred.api.dtos.EstadosTareaDTO;
import com.previred.api.dtos.UsuarioDTO;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TareaResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-24T13:01:33.720141+01:00[Europe/Stockholm]", comments = "Generator version: 7.11.0")
public class TareaResponseDTO {

  private @Nullable Long id;

  private @Nullable String tarea;

  private @Nullable UsuarioDTO usuario;

  private @Nullable EstadosTareaDTO estado;

  public TareaResponseDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TareaResponseDTO tarea(String tarea) {
    this.tarea = tarea;
    return this;
  }

  /**
   * Get tarea
   * @return tarea
   */
  
  @Schema(name = "tarea", example = "tarea1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tarea")
  public String getTarea() {
    return tarea;
  }

  public void setTarea(String tarea) {
    this.tarea = tarea;
  }

  public TareaResponseDTO usuario(UsuarioDTO usuario) {
    this.usuario = usuario;
    return this;
  }

  /**
   * Get usuario
   * @return usuario
   */
  @Valid 
  @Schema(name = "usuario", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usuario")
  public UsuarioDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioDTO usuario) {
    this.usuario = usuario;
  }

  public TareaResponseDTO estado(EstadosTareaDTO estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Get estado
   * @return estado
   */
  @Valid 
  @Schema(name = "estado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("estado")
  public EstadosTareaDTO getEstado() {
    return estado;
  }

  public void setEstado(EstadosTareaDTO estado) {
    this.estado = estado;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TareaResponseDTO tareaResponseDTO = (TareaResponseDTO) o;
    return Objects.equals(this.id, tareaResponseDTO.id) &&
        Objects.equals(this.tarea, tareaResponseDTO.tarea) &&
        Objects.equals(this.usuario, tareaResponseDTO.usuario) &&
        Objects.equals(this.estado, tareaResponseDTO.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tarea, usuario, estado);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TareaResponseDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tarea: ").append(toIndentedString(tarea)).append("\n");
    sb.append("    usuario: ").append(toIndentedString(usuario)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
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

