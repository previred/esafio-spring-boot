package com.nuevospa.taskcontrol.services.impl;

import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import com.nuevospa.taskcontrol.dtos.responses.LoginResponse;
import com.nuevospa.taskcontrol.repositories.UsuarioRepository;
import com.nuevospa.taskcontrol.security.JwtTokenProvider;
import com.nuevospa.taskcontrol.services.UsuarioLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioLoginServiceImpl implements UsuarioLoginService {

    private final UsuarioRepository usuarioRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioLoginService.class);

    @Autowired
    public UsuarioLoginServiceImpl(UsuarioRepository usuarioRepository, JwtTokenProvider jwtTokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LoginResponse loginUsuario(String nombre, String clave) {
        LoginResponse response = null;
        try {
            Optional<Usuario> usuario = this.usuarioRepository.
                    findByNombreAndClave(nombre, clave);
            if(!usuario.isEmpty()) {
                response = new LoginResponse();
                String accessToken = this.jwtTokenProvider
                        .generateAccessToken(usuario.get().getNombre(), usuario.get().getClave());
                response.setAccessToken(accessToken);
                response.setUsuario(usuario.get());
            }
        } catch (Exception e) {
            logger.error("An exception was produced: {}", e);
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }
}
