package com.nuevospa.gestortareas.dto;

import javax.validation.constraints.NotBlank;

public class UpdateTaskDTO {
	
	@NotBlank(message = "El campo 'information' es obligatorio")
    private String information;
	
	@NotBlank(message = "El campo 'status' es obligatorio")
    private String status;
    
   // Getters y Setters 

	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
