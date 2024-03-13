package cl.gestiontareasprevired.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
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
 * Usuarios
 */


@Getter
@Setter
@Entity
@Table(name = "usuarios")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-11T16:53:34.935907-03:00[America/Santiago]")
public class Usuarios {

  @Id
  @Size(max = 255)
  @Column(name = "ID_USER", nullable = false)
  private String idUser;

  @Size(max = 30)
  @Column(name = "NOMBRE_COMPLETO", nullable = false)
  private String nombreCompleto;

  @Size(max = 30)
  @Column(name = "email", nullable = false)
  private String email;

  @Size(max = 255)
  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "FECHA_CREACION")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaCreacion;

}

