package cl.previred.taskmanagement.application.service;

import cl.previred.taskmanagement.core.domain.entities.Rol;
import cl.previred.taskmanagement.core.domain.entities.Usuario;
import cl.previred.taskmanagement.core.domain.entities.UsuarioRol;
import cl.previred.taskmanagement.core.port.UsuarioRepository;
import cl.previred.taskmanagement.core.port.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<UsuarioRol> rolesUsuario = usuarioRolRepository.findByUsuarioRut(usuario.getRut());

        return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPassword(),
                rolesUsuario.stream()
                        .map(usuarioRol -> new SimpleGrantedAuthority(usuarioRol.getRol().getCodigo()))
                        .collect(Collectors.toList()));
    }
}