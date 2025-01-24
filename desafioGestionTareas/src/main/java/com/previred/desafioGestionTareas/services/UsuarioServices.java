package com.previred.desafioGestionTareas.services;

import com.previred.desafioGestionTareas.dtos.ApiResponseDTO;
import com.previred.desafioGestionTareas.dtos.UsuarioDTO;
import org.springframework.http.ResponseEntity;

public interface UsuarioServices {

    ResponseEntity<ApiResponseDTO> login(UsuarioDTO usuarioDTO);
    ResponseEntity<ApiResponseDTO> register(UsuarioDTO usuarioDTO);
    ResponseEntity<ApiResponseDTO> onlytoken();

}
