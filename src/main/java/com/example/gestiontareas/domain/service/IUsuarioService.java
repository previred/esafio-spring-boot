package com.example.gestiontareas.domain.service;

import com.example.gestiontareas.domain.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> findAll();
    public Usuario findById(Long id);
    public Usuario create(Usuario usuario);
    public void deleteById(Long id);
    public Usuario edit(Usuario usuario);

}
