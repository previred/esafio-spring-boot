package cl.gestiontareasprevired.model;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Tarea
 */
@Getter
@Setter
@Entity
@Table(name = "tareas")
@AllArgsConstructor
@NoArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-11T16:53:34.935907-03:00[America/Santiago]")
public class Tarea {

  @Id
  @Size(max = 50)
  @Column(name = "ID_TAREA", nullable = false)
  private String idTarea;

  @Size(max = 50)
  @Column(name = "TITULO_TAREA", nullable = false)
  private String tituloTarea;

  @Size(max = 1000)
  @Column(name = "DESCRIPCION_TAREA", nullable = false)
  private String descripcionTarea;

  @ManyToOne
  @JoinColumn(name = "USUARIO_TAREA", referencedColumnName = "ID_USER", nullable = false)
  private Usuarios usuario;

  @Column(name = "FECHA_INGRESO", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime fechaIngreso;

  @Column(name = "FECHA_ULTIMA_MOD")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime fechaUltimaMod;

  @Column(name = "FECHA_FIN")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime fechaFin;

  @ManyToOne
  @JoinColumn(name = "ESTADO_TAREA", referencedColumnName = "ESTADO_TAREA", nullable = false)
  private EstadosTarea estado;

}

