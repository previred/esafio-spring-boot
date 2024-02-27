package com.example.demo.model;

 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 


 
@Entity
@Table(name = "estadotarea")
public class EstadoTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estado_id;
    
    @Column(name = "nombre")
    private String nombre;

    
	public EstadoTarea() {
	}

	public EstadoTarea(Long estado_id, String nombre) {
		super();
		this.estado_id = estado_id;
		this.nombre = nombre;
	}

	public Long getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(Long estado_id) {
		this.estado_id = estado_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    
   
}