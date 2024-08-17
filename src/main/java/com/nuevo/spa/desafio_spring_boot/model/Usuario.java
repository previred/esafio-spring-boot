package com.nuevo.spa.desafio_spring_boot.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String correo;
    private String contraseña;
    private Date fechaCreacion;
    
    @OneToMany(mappedBy = "usuario")
    private List<Tarea> tareas;
    
	public Usuario() {
		super();
	}

	

	public Usuario(Long id, String nombre, String correo, String contraseña, Date fechaCreacion, List<Tarea> tareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contraseña = contraseña;
		this.fechaCreacion = fechaCreacion;
		this.tareas = tareas;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public List<Tarea> getTareas() {
		return tareas;
	}



	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", contraseña=" + contraseña
				+ ", fechaCreacion=" + fechaCreacion + ", tareas=" + tareas + "]";
	}

	
	
    
    
    
    
    
}

