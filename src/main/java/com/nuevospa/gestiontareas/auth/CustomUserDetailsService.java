package com.nuevospa.gestiontareas.auth;

import com.nuevospa.gestiontareas.data.UsuariosRepository;
import com.nuevospa.gestiontareas.model.security.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuariosRepository userRepository;

    public CustomUserDetailsService(UsuariosRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = userRepository
                            .findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException(""));

        return CustomUserDetails.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(emptyList())
                .build();
    }
}
