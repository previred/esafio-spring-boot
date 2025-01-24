package com.previred.desafioGestionTareas.persistence.Impl;

import com.previred.desafioGestionTareas.entities.Usuario;
import com.previred.desafioGestionTareas.persistence.UsuarioDAO;
import com.previred.desafioGestionTareas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDaoImpl implements UsuarioDAO {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public Optional<Usuario> findByUsername(String name) {

        return userRepository.findByUsername(name);
    }

    @Override
    public void save(Usuario user) {
        userRepository.save(user);
    }

}
