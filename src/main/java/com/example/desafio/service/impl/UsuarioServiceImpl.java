package com.example.desafio.service.impl;

import com.example.desafio.model.entities.Rol;
import com.example.desafio.model.entities.Usuario;
import com.example.desafio.repository.RolRepository;
import com.example.desafio.repository.UsuarioRepository;
import com.example.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        Optional<Rol> optionalRol = rolRepository.findByName("ROL_USUARIO");
        List<Rol> roles = new ArrayList<>();

        optionalRol.ifPresent(roles::add);

        if(usuario.isAdmin()){
            Optional<Rol> optionalRolAdmin = rolRepository.findByName("ROL_ADMINISTRADOR");
            optionalRolAdmin.ifPresent(roles::add);
        }

        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }
}
