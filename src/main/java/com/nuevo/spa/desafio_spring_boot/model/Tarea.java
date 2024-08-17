package com.nuevo.spa.desafio_spring_boot.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descripcion;	
	private Date fechaCreacion;
	private Date fechaLimite;
	private String estado;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private EstadoTarea estadoTarea;

	public Tarea() {
		super();
	}

	
	public Tarea(Long id, String titulo, String descripcion, Date fechaCreacion, Date fechaLimite, String estado,
			Usuario usuario, EstadoTarea estadoTarea) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaLimite = fechaLimite;
		this.estado = estado;
		this.usuario = usuario;
		this.estadoTarea = estadoTarea;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EstadoTarea getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(EstadoTarea estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	

	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Tarea [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaCreacion="
				+ fechaCreacion + ", fechaLimite=" + fechaLimite + ", estado=" + estado + ", usuario=" + usuario
				+ ", estadoTarea=" + estadoTarea + "]";
	}


	

}
