package com.nuevo.spa.taskmanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.nuevo.spa.taskmanagement.model.Usuario;

public interface UsuarioService extends UserDetailsService {
    Usuario findByUsername(String username) throws UsernameNotFoundException;
}
