package com.arturo.desafio_spring_boot.interfaces;

import com.arturo.desafio_spring_boot.dtos.TokenDto;
import com.arturo.desafio_spring_boot.dtos.UsuarioDto;

public interface AuthServiceInterface {
    
    TokenDto login(UsuarioDto user);
    TokenDto validateToken(TokenDto token);

}
