package com.previred.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.entities.UsuarioEntity;
import com.previred.repositories.UsuarioRepository;

/**
 * @author cristian
 */
@Service("usuarioService")
public class UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;
	/**
	 * funcion para almacenar usuario
	 * @param usuarioModel
	 */
	public void guardar(UsuarioEntity usuarioModel) {
		usuarioRepository.save(usuarioModel);
	}
	public UsuarioEntity getByLogin(String id) {
		return usuarioRepository.getReferenceById(id);
	}

}
