package com.previred.model.service;

import com.previred.model.entitys.Usuario;

public interface UsuarioService {
    public Usuario verificarUsuario(String mail,String password);
}
