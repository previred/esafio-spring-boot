package com.desafio.desafiospringboot.model.services;

import com.desafio.desafiospringboot.model.auth.GeneradorJwt;
import com.desafio.desafiospringboot.model.dao.Usuario;
import com.desafio.desafiospringboot.model.exceptions.UserNotFoundException;
import com.desafio.desafiospringboot.model.repositories.UsuarioRepositorioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UsuarioServiceImplement implements UserInterface{
    @Autowired
    private UsuarioRepositorioJPA usuarioRepositorioJPA;

    public UsuarioServiceImplement() {
    }

    public UsuarioServiceImplement(UsuarioRepositorioJPA usuarioRepositorioJPA) {
        this.usuarioRepositorioJPA = usuarioRepositorioJPA;
    }

    @Transactional
    @Override
    public Usuario buscarUser(Usuario user) {
        Optional<Usuario> login= usuarioRepositorioJPA.buscarUsuario(user.getEmail(),user.getPassword());
        if (login.isPresent()){
            Usuario tokenUsuario=login.orElseThrow();
            tokenUsuario.setToken(GeneradorJwt.generarToken());
            return usuarioRepositorioJPA.save(tokenUsuario);
        }else {
            throw new UserNotFoundException("email o password incorrectos");
        }
    }

}
