package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.auth.CustomUserDetails;
import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.model.security.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioMapper {
    private UsuarioMapper() {
        //
    }

    public static Usuario dtoToEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }

        return Usuario.builder()
                .id(usuarioDTO.getId())
                .email(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .nombre(usuarioDTO.getNombre())
                .build();
    }

    public static UsuarioDTO entityToDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .nombre(usuario.getNombre())
                .build();
    }

    public static UserDetails entityToUserDetails(Usuario usr) {
        if (usr == null) {
            return null;
        }

        return CustomUserDetails.builder()
                .username(usr.getEmail())
                .password(usr.getPassword())
                .build();
    }
}
