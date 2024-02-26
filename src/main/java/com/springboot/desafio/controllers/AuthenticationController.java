package com.springboot.desafio.controllers;

import com.springboot.desafio.api.AuthApi;
import com.springboot.desafio.model.AuthRqDTO;
import com.springboot.desafio.model.AuthenticatePost200Response;
import com.springboot.desafio.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationController implements AuthApi {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ResponseEntity<AuthenticatePost200Response> authenticatePost(AuthRqDTO authRqDTO) {
        return new ResponseEntity<>(authenticationService.auth(authRqDTO), HttpStatus.OK);
    }

}
