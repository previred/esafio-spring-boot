package com.nuevospa.dto;

import com.nuevospa.entity.UsuarioEntity;

public class UsuarioDTO {

	private Long id;
	private String nombre;
	private String password;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static UsuarioDTO fromEntity(UsuarioEntity usuarioEntity) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre(usuarioEntity.getNombre());
		usuarioDTO.setPassword(usuarioEntity.getPassword());
		usuarioDTO.setId(usuarioEntity.getId());
		return usuarioDTO;
	}

}
