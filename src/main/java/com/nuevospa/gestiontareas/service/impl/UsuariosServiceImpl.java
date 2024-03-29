package com.nuevospa.gestiontareas.service.impl;

import com.nuevospa.gestiontareas.data.UsuariosRepository;
import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.exception.NotFoundException;
import com.nuevospa.gestiontareas.mapper.UsuarioMapper;
import com.nuevospa.gestiontareas.model.security.Usuario;
import com.nuevospa.gestiontareas.service.UsuariosService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Set<UsuarioDTO> obtenerTodosLosUsuarios() {
        Set<Usuario> usuarios = new HashSet<>(usuariosRepository.findAll());
        return usuarios.stream()
                .map(UsuarioMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorEmail(String email) throws NotFoundException {
        Usuario usr = usuariosRepository
                .findByEmail(email)
                .orElseThrow(NotFoundException::new);
        return UsuarioMapper.entityToDto(usr);
    }

    @Override
    public UserDetails obtenerUserDetails(String email) throws NotFoundException {
        Usuario usr = usuariosRepository
                .findByEmail(email)
                .orElseThrow(NotFoundException::new);
        return UsuarioMapper.entityToUserDetails(usr);
    }
}
