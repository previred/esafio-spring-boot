package cl.tecnova.pruebatecnica.controller;

import cl.tecnova.pruebatecnica.dto.LoginUserRequest;
import cl.tecnova.pruebatecnica.dto.TokenResponse;
import cl.tecnova.pruebatecnica.exception.HTTPException;
import cl.tecnova.pruebatecnica.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuariosController implements UsuariosApi {

    @Autowired
    private UsuariosService userService;

    @Override
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginUserRequest request) throws HTTPException {
        TokenResponse response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
