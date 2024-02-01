package com.desafio.spring.desafiospringboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Estado_Tarea")
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTarea {
	
	@Id
	private Long id_estado;
	private String nombre_estado;

}
