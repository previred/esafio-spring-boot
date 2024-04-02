package com.previred.nspa.model;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeName("Tareas")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-01T19:02:29.794985500-03:00[America/Santiago]")
public class TareasDTO {

  private Integer idTarea;

  @NotNull
  private UsuariosDTO usuario;

  @NotNull
  @Size(min = 3, max = 100)
  private String titulo;

  @Size(max = 255)
  private String descripcion;

  @NotNull
  @PastOrPresent
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date fechaCreacion;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date fechaLimite;

  @NotNull
  private EstadosTareaDTO estadoTarea;


  public TareasDTO idTarea(Integer idTarea) {
    this.idTarea = idTarea;
    return this;
  }

  /**
   * Get idTarea
   * @return idTarea
  */
  
  @Schema(name = "idTarea", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("idTarea")
  public Integer getIdTarea() {
    return idTarea;
  }

  public void setIdTarea(Integer idTarea) {
    this.idTarea = idTarea;
  }

  public TareasDTO usuario(UsuariosDTO usuario) {
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
  public UsuariosDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuariosDTO usuario) {
    this.usuario = usuario;
  }

  public TareasDTO titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
  */
  
  @Schema(name = "titulo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("titulo")
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public TareasDTO descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
  */
  
  @Schema(name = "descripcion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("descripcion")
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public TareasDTO fechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Get fechaCreacion
   * @return fechaCreacion
  */
  @Valid 
  @Schema(name = "fechaCreacion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fechaCreacion")
  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public TareasDTO fechaLimite(Date fechaLimite) {
    this.fechaLimite = fechaLimite;
    return this;
  }

  /**
   * Get fechaLimite
   * @return fechaLimite
  */
  @Valid 
  @Schema(name = "fechaLimite", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fechaLimite")
  public Date getFechaLimite() {
    return fechaLimite;
  }

  public void setFechaLimite(Date fechaLimite) {
    this.fechaLimite = fechaLimite;
  }

  public TareasDTO estadoTarea(EstadosTareaDTO estadoTarea) {
    this.estadoTarea = estadoTarea;
    return this;
  }

  /**
   * Get estadoTarea
   * @return estadoTarea
  */
  @Valid 
  @Schema(name = "estadoTarea", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("estadoTarea")
  public EstadosTareaDTO getEstadoTarea() {
    return estadoTarea;
  }

  public void setEstadoTarea(EstadosTareaDTO estadoTarea) {
    this.estadoTarea = estadoTarea;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TareasDTO tareas = (TareasDTO) o;
    return Objects.equals(this.idTarea, tareas.idTarea) &&
        Objects.equals(this.usuario, tareas.usuario) &&
        Objects.equals(this.titulo, tareas.titulo) &&
        Objects.equals(this.descripcion, tareas.descripcion) &&
        Objects.equals(this.fechaCreacion, tareas.fechaCreacion) &&
        Objects.equals(this.fechaLimite, tareas.fechaLimite) &&
        Objects.equals(this.estadoTarea, tareas.estadoTarea);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTarea, usuario, titulo, descripcion, fechaCreacion, fechaLimite, estadoTarea);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TareasDTO {\n");
    sb.append("    idTarea: ").append(toIndentedString(idTarea)).append("\n");
    sb.append("    usuario: ").append(toIndentedString(usuario)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    fechaLimite: ").append(toIndentedString(fechaLimite)).append("\n");
    sb.append("    estadoTarea: ").append(toIndentedString(estadoTarea)).append("\n");
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

