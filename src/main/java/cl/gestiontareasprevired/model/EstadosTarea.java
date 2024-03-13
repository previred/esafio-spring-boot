package cl.gestiontareasprevired.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * EstadosTarea
 */
@Getter
@Setter
@Entity
@Table(name = "estados_tarea")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-11T16:53:34.935907-03:00[America/Santiago]")
public class EstadosTarea {

  @Id
  @Size(max = 10)
  @Column(name = "ESTADO_TAREA", nullable = false)
  private String estadoTarea;

  @Size(max = 50)
  @Column(name = "DESCRIPCION_ESTADO", nullable = false)
  private String descripcionEstado;

}

