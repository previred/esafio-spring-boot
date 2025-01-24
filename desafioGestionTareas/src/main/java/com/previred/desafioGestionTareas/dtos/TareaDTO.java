package com.previred.desafioGestionTareas.dtos;

import com.previred.desafioGestionTareas.entities.TareaEstado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDTO{

    private Long id;

    private String description;

    private TareaEstado state;

    // Getter y Setter para id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter y Setter para state
    public TareaEstado getState() {
        return state;
    }

    public void setState(TareaEstado state) {
        this.state = state;
    }

}