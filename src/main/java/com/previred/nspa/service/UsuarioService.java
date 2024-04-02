package com.previred.nspa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.previred.nspa.entity.Usuarios;
import com.previred.nspa.model.UsuariosDTO;
import com.previred.nspa.repository.UsuariosRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean validarUsuario(UsuariosDTO usuarioDTO) {
    	Usuarios usuario = usuariosRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario());
        
        
        return usuario != null && bCryptPasswordEncoder.matches(usuarioDTO.getContrasenaHash(), usuario.getContrasenaHash());
    }
    
    public Usuarios validarUsuario(String correoElectronico, String contrasena) {
        Usuarios usuario = usuariosRepository.findByCorreoElectronico(correoElectronico);
        if (usuario != null && bCryptPasswordEncoder.matches(contrasena, usuario.getContrasenaHash())) {
            return usuario;
        }
        return null; // O lanzar una excepción si prefieres manejarlo de esa manera
    }
}
