package com.previred.gestion_tareas_api.services;

import org.springframework.http.ResponseEntity;

import com.previred.gestion_tareas_api.dtos.ApiResponseDTO;
import com.previred.gestion_tareas_api.dtos.UserDTO;

public interface UserService {


    ResponseEntity<ApiResponseDTO> register(UserDTO userDTO);
    ResponseEntity<ApiResponseDTO> login(UserDTO userDTO);

}
