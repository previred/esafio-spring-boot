package com.desafio.spring.desafiospringboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Tarea")
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_tarea;
	private String nombreTarea;
	private String descripcionTarea;
	
	@ManyToOne
	@JoinColumn(name = "id_estado")
	EstadoTarea estado;

}
