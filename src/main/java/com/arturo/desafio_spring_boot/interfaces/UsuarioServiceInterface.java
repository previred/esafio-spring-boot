package com.arturo.desafio_spring_boot.interfaces;

import com.arturo.desafio_spring_boot.entities.UsuarioEntity;

public interface UsuarioServiceInterface {
    
    public UsuarioEntity getById(Long id);
    public UsuarioEntity getByNombreUsuario(String nombreUsuario);

}
