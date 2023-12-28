package com.example.gestiontareas.domain.service.impl;


import com.example.gestiontareas.domain.model.Usuario;
import com.example.gestiontareas.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class JwtUserDetailsService implements UserDetailsService {


    private final UsuarioRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UsuarioRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Usuario> usuarioList = (List<Usuario>) userRepository.findAll();
        if (!ObjectUtils.isEmpty(usuarioList)){
            Usuario user = usuarioList.stream().filter(e -> e.getUsername().equals(username)).findFirst().orElse(null);
            if (ObjectUtils.isEmpty(user)) {
                throw new UsernameNotFoundException(String.format("User not exists", username));
            }
            List<GrantedAuthority> roles = new ArrayList<>();
            user.getRoles().forEach(rol -> {
                roles.add(new SimpleGrantedAuthority(rol.getRol()));
            });
            UserDetails ud = new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, roles);
            return ud;
        }
        return null;
    }
}