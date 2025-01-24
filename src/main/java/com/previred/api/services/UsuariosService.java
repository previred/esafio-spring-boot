package com.previred.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.api.dtos.UsuarioDTO;
import com.previred.api.models.Usuarios;
import com.previred.api.repositories.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuariosService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuarios> userDetail = usuariosRepository.findByName(username);
        return userDetail.map(UsuariosDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado " + username));
    }

    public String agregarUsuario(Usuarios usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuariosRepository.save(usuario);
        return "Usuario creado exitosamente!";
    }

    public UsuarioDTO buscarPorId(Long id) {
        Optional<Usuarios> usuario = usuariosRepository.findById(id);
        if (usuario.isPresent()) {
            return objectMapper.convertValue(usuario.get(), UsuarioDTO.class);
        }
        return null;
    }

}
