package com.springboot.desafio.controllers;

import com.springboot.desafio.api.TareasApi;
import com.springboot.desafio.model.*;
import com.springboot.desafio.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class TareaController implements TareasApi {

    @Autowired
    private TareaService tareaService;

    @Override
    public ResponseEntity<TareaRsDTO> createTarea(TareaRqDTO tareaRqDTO) {
        TareaRsDTO tareaRsDTO = tareaService.crearTarea(tareaRqDTO);
        return new ResponseEntity<>(tareaRsDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTareaById(Long id) {
        tareaService.eliminarTarea(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<TareaRsDTO> getTareaById(Long id) {
        return new ResponseEntity<>(tareaService.getTareaById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TareaRsDTO>> getTareas() {
        return new ResponseEntity<>(tareaService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TareaRsDTO> updateTareaById(Long id, TareaEstRqDTO tareaEstRqDTO) {
        return new ResponseEntity<>(tareaService.updateTareaById(id, tareaEstRqDTO), HttpStatus.OK);
    }

}
