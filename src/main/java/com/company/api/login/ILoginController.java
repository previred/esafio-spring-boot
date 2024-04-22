package com.company.api.login;

import com.company.model.LoginRequest;
import com.company.model.TokenResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ILoginController {

    @ApiOperation(value = "Servicio que permite autenticar al usuario ",
            notes = "El servicio retorna un  token con el cual puede acceder a la aplicacion",
            nickname = "login",
            response = Void.class)
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest login);
}
