package com.previred.desafio.service;

import com.previred.desafio.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UsuarioService.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

public interface UsuarioService extends UserDetailsService {
    Usuario findByUsername(String username) throws UsernameNotFoundException;
}
