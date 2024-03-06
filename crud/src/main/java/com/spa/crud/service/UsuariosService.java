package com.spa.crud.service;

import com.spa.crud.dto.UsuariosDTO;
import com.spa.crud.model.Usuarios;

import java.util.List;

public interface UsuariosService {
    UsuariosDTO loadUserByUser(Usuarios user) throws Exception;
    void createNewUser(Usuarios user);
    List<UsuariosDTO> getAllUsers();
    void deleteUser(String username);
}
