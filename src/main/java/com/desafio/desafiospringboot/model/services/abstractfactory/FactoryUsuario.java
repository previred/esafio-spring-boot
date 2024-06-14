package com.desafio.desafiospringboot.model.services.abstractfactory;

import com.desafio.desafiospringboot.model.dao.Usuario;
import com.desafio.desafiospringboot.model.services.UsuarioServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryUsuario {
    @Autowired
    private AbstractFactory abstractFactory;

    public Usuario buscarUser(Usuario user){
        UsuarioServiceImplement usuarioServiceImplement= (UsuarioServiceImplement) abstractFactory.crearUsuario();
        Usuario usuario=usuarioServiceImplement.buscarUser(user);
        return usuario;
    }



}
