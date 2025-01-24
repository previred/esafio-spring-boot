package com.previred.desafioGestionTareas.services.Impl;

import com.previred.desafioGestionTareas.configs.ModelMapperConfig;
import com.previred.desafioGestionTareas.dtos.ApiResponseDTO;
import com.previred.desafioGestionTareas.dtos.UsuarioDTO;
import com.previred.desafioGestionTareas.entities.Usuario;
import com.previred.desafioGestionTareas.persistence.UsuarioDAO;
import com.previred.desafioGestionTareas.services.UsuarioServices;
import com.previred.desafioGestionTareas.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioServices {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<ApiResponseDTO> register(UsuarioDTO usuarioDTO) {

        Optional<Usuario> existingUser = usuarioDAO.findByUsername(usuarioDTO.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(new ApiResponseDTO(false, "El Usuario ya se encuentra registrado en nuestro sistema, favor Inicia Sesion",usuarioDTO.getUsername() ), HttpStatus.BAD_REQUEST);
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usuarioDTO.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuarioDAO.save(nuevoUsuario);

        return new ResponseEntity<>(new ApiResponseDTO(true, "Usuario registrado con éxito", usuarioDTO.getUsername()), HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<ApiResponseDTO> login(UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioDAO.findByUsername(usuarioDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(usuarioDTO.getPassword(), usuario.getPassword())) {

            return new ResponseEntity<>(new ApiResponseDTO(false, "Usuario o contraseña incorrectos", usuarioDTO.getUsername()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ApiResponseDTO(true, "Inicio de sesion con éxito", jwtUtil.generateToken(usuarioDTO.getUsername())), HttpStatus.OK);
    }


    //ONLY DEBUGGER DEVELOPER
    @Override
    public ResponseEntity<ApiResponseDTO> onlytoken() {

        return new ResponseEntity<>(new ApiResponseDTO(true, "Inicio de sesion con éxito", jwtUtil.generateToken("rene1").toString()), HttpStatus.OK);
    }
}