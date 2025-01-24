package com.previred.api.dtos;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TareaDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-24T13:01:33.720141+01:00[Europe/Stockholm]", comments = "Generator version: 7.11.0")
public class TareaDTO {

  private @Nullable Long id;

  private @Nullable String tarea;

  private @Nullable Long usuarioId;

  private @Nullable Long estadoId;

  public TareaDTO id(Long id) {
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

  public TareaDTO tarea(String tarea) {
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

  public TareaDTO usuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
    return this;
  }

  /**
   * Get usuarioId
   * @return usuarioId
   */
  
  @Schema(name = "usuarioId", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usuarioId")
  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public TareaDTO estadoId(Long estadoId) {
    this.estadoId = estadoId;
    return this;
  }

  /**
   * Get estadoId
   * @return estadoId
   */
  
  @Schema(name = "estadoId", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("estadoId")
  public Long getEstadoId() {
    return estadoId;
  }

  public void setEstadoId(Long estadoId) {
    this.estadoId = estadoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TareaDTO tareaDTO = (TareaDTO) o;
    return Objects.equals(this.id, tareaDTO.id) &&
        Objects.equals(this.tarea, tareaDTO.tarea) &&
        Objects.equals(this.usuarioId, tareaDTO.usuarioId) &&
        Objects.equals(this.estadoId, tareaDTO.estadoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tarea, usuarioId, estadoId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TareaDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tarea: ").append(toIndentedString(tarea)).append("\n");
    sb.append("    usuarioId: ").append(toIndentedString(usuarioId)).append("\n");
    sb.append("    estadoId: ").append(toIndentedString(estadoId)).append("\n");
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

