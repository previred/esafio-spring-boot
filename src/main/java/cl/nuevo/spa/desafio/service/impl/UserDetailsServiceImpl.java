package cl.nuevo.spa.desafio.service.impl;

import cl.nuevo.spa.desafio.model.Usuario;
import cl.nuevo.spa.desafio.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPass(),
                Collections.emptyList());
    }

}
