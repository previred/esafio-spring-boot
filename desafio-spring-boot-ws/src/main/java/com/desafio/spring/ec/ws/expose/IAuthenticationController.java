package com.desafio.spring.ec.ws.expose;

import com.desafio.spring.ec.ds.request.UserLogin;
import com.desafio.spring.ec.ds.request.UserRegister;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.desafio.spring.ec.ws.common.SwaggerConstants.AUTH_DESCRIPTION;
import static com.desafio.spring.ec.ws.common.SwaggerConstants.AUTH_TAG;

@Tag(name = AUTH_TAG, description = AUTH_DESCRIPTION)
@RequestMapping("/api/auth")
public interface IAuthenticationController {

    @Operation(summary = "Registra um usuário")
    @PostMapping("/register")
    String userRegister(@RequestBody UserRegister userRegister);

    @Operation(summary = "Realiza login de um usuário")
    @PostMapping("/login")
    String userLogin(@RequestBody UserLogin userLogin);
}
