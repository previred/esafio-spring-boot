package com.desafio.spring.desafiospringboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	private Long id_usuario;
	private String nombre_usuario;
	private String username;
	private String password;

}
