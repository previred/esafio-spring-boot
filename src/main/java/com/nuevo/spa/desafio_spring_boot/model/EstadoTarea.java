package com.nuevo.spa.desafio_spring_boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EstadoTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nombre;    
    
    
	public EstadoTarea() {
		super();
	}


	public EstadoTarea(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


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


	@Override
	public String toString() {
		return "EstadoTarea [id=" + id + ", nombre=" + nombre + "]";
	}
    
	
	
    
}

