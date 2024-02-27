package com.nuevospa.dto;

import com.nuevospa.entity.TareaEntity;

public class TareaIdDTO {

	private Long id;
	private Long idEstadoTarea;
	private String nombreTarea;
	private String descripcionTarea;
	

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

	public Long getIdEstadoTarea() {
		return idEstadoTarea;
	}

	public void setIdEstadoTarea(Long idEstadoTarea) {
		this.idEstadoTarea = idEstadoTarea;
	}
	
	public static TareaIdDTO fromEntity(TareaEntity tareaEntity) {
		TareaIdDTO tareaDTO = new TareaIdDTO();
		tareaDTO.setIdEstadoTarea(tareaEntity.getId());
		tareaDTO.setNombreTarea(tareaEntity.getNombreTarea());
		tareaDTO.setDescripcionTarea(tareaEntity.getDescripcionTarea());
		tareaDTO.setId(tareaEntity.getId());
		return tareaDTO;
	}

	public TareaIdDTO() {}
		
	
	public TareaIdDTO(TareaEntity tareaEntity) {
		this.idEstadoTarea = tareaEntity.getEstadoTarea().getId();
		this.nombreTarea = tareaEntity.getNombreTarea();
		this.descripcionTarea = tareaEntity.getDescripcionTarea();
		this.id = tareaEntity.getId();
    }
	
}
