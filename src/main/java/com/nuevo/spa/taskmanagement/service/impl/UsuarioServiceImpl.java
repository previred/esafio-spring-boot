package com.nuevo.spa.taskmanagement.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nuevo.spa.taskmanagement.constants.Messages;
import com.nuevo.spa.taskmanagement.model.Usuario;
import com.nuevo.spa.taskmanagement.repository.UsuarioRepository;
import com.nuevo.spa.taskmanagement.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(Messages.USER_NOT_FOUND);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }

    @Override
    public Usuario findByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(Messages.USER_NOT_FOUND);
        }
        return usuario;
    }
}
