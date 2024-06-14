package com.desafio.desafiospringboot.controller;

import com.desafio.desafiospringboot.model.dao.Usuario;
import com.desafio.desafiospringboot.model.services.abstractfactory.FactoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import com.desafio.desafiospringboot.model.dto.Error;
@RestController
public class UsuarioController {
    @Autowired
    private FactoryUsuario factoryUsuario;

    @PostMapping("/login")
    public ResponseEntity<?> loginDeUsuario(@Valid @RequestBody Usuario user, BindingResult result){
        ResponseEntity<Usuario> usuario;
        ResponseEntity<List<Error>> errores;
        if (result.hasErrors()){
            List<Error> error=new ArrayList<>();
            result.getFieldErrors().forEach(err ->{
                error.add(new Error(err.getDefaultMessage()));
            });
            errores=new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            return errores;
        }
        usuario=new ResponseEntity<>(factoryUsuario.buscarUser(user),HttpStatus.OK);
        return usuario;
    }



}
