package com.move.challenge.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TareaCreateDto {

   private String nombre;

   private String descripcion;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime fechaFinalizacion;

   private Long usuario;

   private Long estado;


}
