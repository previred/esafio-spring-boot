package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


 
@Entity
@Table(name = "tarea")
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	@ManyToOne
	@JoinColumn(name = "estado_id") 
	private EstadoTarea estado;
	
	
	public Tarea(Long id, String titulo, String descripcion, Usuario usuario, EstadoTarea estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.estado = estado;
	}
	public Tarea() {
 
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public EstadoTarea getEstado() {
		return estado;
	}
	public void setEstado(EstadoTarea estado) {
		this.estado = estado;
	}
	
	

}
