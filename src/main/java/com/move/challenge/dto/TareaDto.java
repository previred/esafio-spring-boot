package com.move.challenge.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TareaDto {

   private Long id;

   private String nombre;

   private String descripcion;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime fechaCreacion;

   private String creadoPor;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime fechaModificacion;

   private String modificadoPor;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime fechaFinalizacion;

   private UsuarioMinDto usuario;

   private EstadoTareaDto estado;

}
