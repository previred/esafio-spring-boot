package com.nuevospa.interfaces;

import com.nuevospa.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    boolean validarUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> listar();

    boolean validarNombre(String nombre);

    void insertarUsuario(UsuarioDTO usuarioDTO);

	Optional<UsuarioDTO> actualizarUsuario(UsuarioDTO usuarioDTO);
}