package com.example.gestiontareas.domain.service.impl;

import com.example.gestiontareas.domain.model.Usuario;
import com.example.gestiontareas.domain.repository.UsuarioRepository;
import com.example.gestiontareas.domain.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {


    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    @Override
    public Usuario edit(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


}
