package com.nuevospa.gestortareas.dto;

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String information;
    private String username; // Nombre de usuario del creador
    private String statusName; // Nombre del estado de la tarea

    // Constructor
    public TaskResponseDTO(Long id, String title, String description, String information, String username, String statusName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.information = information;
        this.username = username;
        this.statusName = statusName;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
