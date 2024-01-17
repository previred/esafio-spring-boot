package com.nuevospa.taskcontrol.services;

import com.nuevospa.taskcontrol.dtos.responses.LoginResponse;

public interface UsuarioLoginService {

    LoginResponse loginUsuario(String nombre, String clave);
}
