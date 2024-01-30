package cl.nuevo.spa.desafio.service;


import cl.nuevo.spa.desafio.dto.UsuarioDTO;
import cl.nuevo.spa.desafio.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> findAll();

    Optional<UsuarioDTO> findById(Integer id);

    UsuarioDTO save(Usuario usuario);

    void delete(Integer id);
}
