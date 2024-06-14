package com.desafio.spring.ec.ws.expose.impl;


import com.desafio.spring.ec.bs.services.config.AuthenticationService;
import com.desafio.spring.ec.ds.request.UserLogin;
import com.desafio.spring.ec.ds.request.UserRegister;
import com.desafio.spring.ec.ws.expose.IAuthenticationController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements IAuthenticationController {

    private final AuthenticationService authenticationService;


    public String userRegister(@RequestBody UserRegister userRegister) {
        return authenticationService.register(userRegister);
    }

    public String userLogin(@RequestBody UserLogin userLogin) {
        return authenticationService.login(userLogin);
    }

}
