package com.nuevospa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nuevospa.dto.TareaDTO;
import com.nuevospa.dto.TareaIdDTO;


@Entity
@Table(name = "tareas")
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreTarea;
    private String descripcionTarea;

    @ManyToOne
    private EstadoTareaEntity estadoTarea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public String getDescripcionTarea() {
		return descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	public EstadoTareaEntity getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(EstadoTareaEntity estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public TareaEntity(TareaDTO tareaDto, EstadoTareaEntity estadoTarea) {
		this.nombreTarea = tareaDto.getNombreTarea();
		this.descripcionTarea = tareaDto.getDescripcionTarea();
		this.setEstadoTarea(estadoTarea);
	}

    public TareaEntity() {
    }
    
    public TareaEntity(TareaIdDTO tarea, EstadoTareaEntity estadoTarea) {
    	this.id = tarea.getId();
    	this.nombreTarea = tarea.getNombreTarea();
    	this.descripcionTarea = tarea.getDescripcionTarea();
    	this.setEstadoTarea(estadoTarea);
    }
}
