package com.nuevospa.gestortareas.dto;

import javax.validation.constraints.NotBlank;

public class CreateTaskDTO {
	@NotBlank(message = "El título de la tarea es obligatorio")
    private String title;
	
	@NotBlank(message = "La descripción de la tarea es obligatoria")
    private String description;
	 
    private String information;

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
