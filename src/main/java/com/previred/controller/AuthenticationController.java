package com.previred.controller;

import com.previred.dto.ResponseError;
import com.previred.dto.ResponseToken;
import com.previred.jwt.JwtUserService;
import com.previred.jwt.JwtUtil;
import com.previred.model.entitys.Usuario;
import com.previred.model.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
@ApiResponse(responseCode = "200",description = "OK",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseToken.class))
})
@ApiResponse(responseCode = "400",description = "La solicitud es incorrecta",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseError.class))
})
@ApiResponse(responseCode = "401",description = "Solicitud no autorizada",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseError.class))
})
@ApiResponse(responseCode = "500",description = "Error servidor",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseError.class))
})
public class AuthenticationController {

    final UsuarioService usuarioService;
    final JwtUserService userService;
    final JwtUtil jwtUtil;

    public AuthenticationController(UsuarioService usuarioService, JwtUserService userService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    @Operation(summary = "Permite autenticar un usuario",description = "mostrar")
    @PostMapping(value = "getTokenUsuario",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTokenUsuario(@RequestParam(name = "mail") String mail, @RequestParam(name = "password") String password){
        try {
            mail = mail.trim().toUpperCase();
            Usuario usuario = usuarioService.verificarUsuario(mail, password);
            if (usuario == null) {
                ResponseError response = new ResponseError(0, "200", "Mail y/o password incorrecto/s");
                return ResponseEntity.ok(response);
            }
            UserDetails userDetails = userService.loadUserByUsername(usuario.getMail());
            String token = jwtUtil.generateToken(userDetails);
            ResponseToken responseToken = new ResponseToken(0, "Token generado correctamente", token);
            return ResponseEntity.ok(responseToken);
        }catch (Exception e){
            ResponseError responseError=new ResponseError(1,"500","Error al momento de generar el token");
            return ResponseEntity.ok(responseError);
        }
    }
}
