package com.nuevospa.dto;

import com.nuevospa.entity.TareaEntity;

public class TareaDTO {

	private Long idEstadoTarea;
	private String nombreTarea;
	private String descripcionTarea;
	

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
	
	public static TareaDTO fromEntity(TareaEntity tareaEntity) {
		TareaDTO tareaDTO = new TareaDTO();
		tareaDTO.setIdEstadoTarea(tareaEntity.getId());
		tareaDTO.setNombreTarea(tareaEntity.getNombreTarea());
		tareaDTO.setDescripcionTarea(tareaEntity.getDescripcionTarea());
		return tareaDTO;
	}

	public TareaDTO() {}
		
	
	public TareaDTO(TareaEntity tareaEntity) {
		this.idEstadoTarea = tareaEntity.getEstadoTarea().getId();
		this.nombreTarea = tareaEntity.getNombreTarea();
		this.descripcionTarea = tareaEntity.getDescripcionTarea();
    }
	
}
