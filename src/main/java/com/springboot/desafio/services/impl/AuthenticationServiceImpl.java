package com.springboot.desafio.services.impl;

import com.springboot.desafio.constants.Constantes;
import com.springboot.desafio.exceptions.LoginException;
import com.springboot.desafio.model.AuthRqDTO;
import com.springboot.desafio.model.AuthenticatePost200Response;

import com.springboot.desafio.services.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.springboot.desafio.constants.Constantes.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticatePost200Response auth(AuthRqDTO authRqDTO){
        AuthenticatePost200Response response = new AuthenticatePost200Response();
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRqDTO.getUsername(),
                            authRqDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            final String jwt = Jwts.builder()
                    .setSubject(authRqDTO.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRED))
                    .signWith(SignatureAlgorithm.HS256, Constantes.SECRET_KEY)
                    .compact();

            response.setCodigo(CODIGO_OK);
            response.setMensaje(jwt);
            return response;
        } catch (Exception e) {
            throw new LoginException("Error al intentar login." + e.getMessage());
        }
    }
}
