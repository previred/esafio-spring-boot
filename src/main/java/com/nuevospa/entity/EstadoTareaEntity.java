package com.nuevospa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_tarea")
public class EstadoTareaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoTareaEntity(String nombre) {
        this.nombre = nombre;
    }
	
	public EstadoTareaEntity(Long id,String nombre) {
        this.id = id;
		this.nombre = nombre;
    }
	public EstadoTareaEntity() {
        
    }
	
	
	
}