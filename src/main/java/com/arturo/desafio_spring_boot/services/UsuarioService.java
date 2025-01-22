package com.arturo.desafio_spring_boot.services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.arturo.desafio_spring_boot.entities.UsuarioEntity;
import com.arturo.desafio_spring_boot.interfaces.UsuarioServiceInterface;
import com.arturo.desafio_spring_boot.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioServiceInterface {

    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity getById(Long id) throws NoSuchElementException {
        return this.usuarioRepository.findById(id).get();
    }

    @Override
    public UsuarioEntity getByNombreUsuario(String nombreUsuario) {
        return this.usuarioRepository.findByNombreUsuario(nombreUsuario).get();
    }

}
