package com.previred.desafioApiRest.desafioprevired.service.impl;


import com.previred.desafioApiRest.desafioprevired.repository.UsuarioRepository;
import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;
import com.previred.desafioApiRest.desafioprevired.repository.model.Usuario;
import com.previred.desafioApiRest.desafioprevired.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    @Override
    public Usuario findById(Long id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.get();
    }

    @Override
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarTarea(Long id){
        usuarioRepository.deleteById(id);
    }
}
