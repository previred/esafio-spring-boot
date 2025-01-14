package com.previred.gestion_tareas_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.previred.gestion_tareas_api.configs.ModelMapperConfig;
import com.previred.gestion_tareas_api.dtos.ApiResponseDTO;
import com.previred.gestion_tareas_api.dtos.UserDTO;
import com.previred.gestion_tareas_api.entities.User;
import com.previred.gestion_tareas_api.persistence.UserDAO;
import com.previred.gestion_tareas_api.services.UserService;
import com.previred.gestion_tareas_api.utils.JwtUtil;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private ModelMapperConfig modelMapper;

    
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Autowired
    private JwtUtil jwtUtil;




    @Override
    public ResponseEntity<ApiResponseDTO> register(UserDTO userDTO) {

        Optional<User> existingUser = userDAO.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(new ApiResponseDTO(false, "El usuario ya esta registrado, por favor inicia sesion",userDTO.getUsername() ), HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDAO.save(newUser);

        
        return new ResponseEntity<>(new ApiResponseDTO(true, "Usuario registrado con éxito", userDTO.getUsername()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> login(UserDTO userDTO) {
    User user = userDAO.findByUsername(userDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

    
    if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {

        return new ResponseEntity<>(new ApiResponseDTO(false, "Usuario o contraseña incorrectos", userDTO.getUsername()), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(new ApiResponseDTO(true, "Inicio de sesion con éxito", "Bearer " + jwtUtil.generateToken(userDTO.getUsername())), HttpStatus.OK);
}






    

    

}
