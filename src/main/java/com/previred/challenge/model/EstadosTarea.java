package com.previred.challenge.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ESTADOS_TAREA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadosTarea{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String status;
	
	@OneToMany(mappedBy = "statusTask")
    private Set<Tareas> tareas = new HashSet<>();
}
