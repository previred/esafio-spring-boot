package com.nuevospa.dto;

import com.nuevospa.entity.EstadoTareaEntity;

public class EstadoTareaDTO {

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

	public static EstadoTareaDTO fromEntity(EstadoTareaEntity estadoTareaEntity) {
		EstadoTareaDTO estadoTareaDTO = new EstadoTareaDTO();
		estadoTareaDTO.setId(estadoTareaEntity.getId());
		estadoTareaDTO.setNombre(estadoTareaEntity.getNombre());
		return estadoTareaDTO;
	}

	public EstadoTareaDTO() {
	}

	public EstadoTareaDTO(EstadoTareaEntity estadoTareaEntity) {
		this.id = estadoTareaEntity.getId();
		this.nombre = estadoTareaEntity.getNombre();

	}

}
