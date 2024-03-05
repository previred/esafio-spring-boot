package com.example.desafio.service;

import com.example.desafio.model.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario crearUsuario(Usuario usuario);
}
