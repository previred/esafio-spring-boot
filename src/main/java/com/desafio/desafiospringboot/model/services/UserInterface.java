package com.desafio.desafiospringboot.model.services;

import com.desafio.desafiospringboot.model.dao.Usuario;

public interface UserInterface {
    Usuario buscarUser(Usuario user);
}
