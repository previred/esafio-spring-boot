package com.springboot.desafio.services;

import com.springboot.desafio.model.AuthRqDTO;
import com.springboot.desafio.model.AuthenticatePost200Response;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticationService {

    AuthenticatePost200Response auth(AuthRqDTO authRqDTO);
}
