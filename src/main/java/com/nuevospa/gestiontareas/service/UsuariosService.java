package com.nuevospa.gestiontareas.service;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface UsuariosService {
    Set<UsuarioDTO> obtenerTodosLosUsuarios();

    UsuarioDTO obtenerUsuarioPorEmail(String email) throws NotFoundException;

    UserDetails obtenerUserDetails(String email) throws NotFoundException;
}
