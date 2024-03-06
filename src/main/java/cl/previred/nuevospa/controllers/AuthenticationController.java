package cl.previred.nuevospa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.nuevospa.business.UsuarioBusiness;
import cl.previred.nuevospa.dto.CredentialsDto;
import cl.previred.nuevospa.dto.LoginDto;
import cl.previred.nuevospa.dto.ResponseApiDto;
import cl.previred.nuevospa.exceptions.ElementoNoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UsuarioBusiness userBusiness;

    @Operation(summary = "Login para usuarios", description = "Endpoint donde poner nombre de usuario y contraseña para obtener token JWT.", tags={ "Autenticación" })
    @PostMapping("/login")
    public ResponseEntity<ResponseApiDto> postMethodName(@RequestBody LoginDto login) {
        try {
            CredentialsDto respuesta = userBusiness.login(login);
            return new ResponseEntity<ResponseApiDto>(new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), respuesta), HttpStatus.OK);
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(new ResponseApiDto(e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<ResponseApiDto>(new ResponseApiDto(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
