package com.previred.desafio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Tarea.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Entity
@Data
public class Tarea {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String description;

	    @ManyToOne
	    @JoinColumn(name = "estado_tarea_id")
	    private EstadoTarea status;

}