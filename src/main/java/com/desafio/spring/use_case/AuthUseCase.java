package com.desafio.spring.use_case;

import com.desafio.spring.config.security.UserDetailsImpl;
import com.desafio.spring.config.security.jwt.JwtUtils;
import com.desafio.spring.service.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.openapitools.api.AuthApi;
import org.openapitools.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class AuthUseCase implements AuthApi {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtils jwtUtils;

    public AuthUseCase(UserDetailsServiceImpl userDetailsService,
                   JwtUtils jwtUtils){

        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public ResponseEntity<Auth> auth(
            @NotNull @Parameter(name = "email", description = "ID Task", required = true, in = ParameterIn.QUERY)
            @Valid @RequestParam(value = "email", required = true) String email) {
        UserDetailsImpl user = userDetailsService.loadUserByUsername(email);
        String jwt = jwtUtils.generateJwtToken(user);
        Auth auth = new Auth();
        auth .setJwt(com.desafio.spring.model.Auth.builder().jwt(jwt).build().getJwt());
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }
}
