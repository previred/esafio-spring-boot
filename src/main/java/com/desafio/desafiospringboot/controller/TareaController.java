package com.desafio.desafiospringboot.controller;

import com.desafio.desafiospringboot.model.dao.Tarea;
import com.desafio.desafiospringboot.model.dto.Error;
import com.desafio.desafiospringboot.model.services.abstractfactory.FactoryTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import com.desafio.desafiospringboot.model.dto.Error;
@SuppressWarnings("DuplicatedCode")
@RestController
public class TareaController {
    @Autowired
    private FactoryTarea factoryTarea;

    @GetMapping("/listar-tareas/{id}")
    public ResponseEntity<List<Tarea>> listarTareas(@PathVariable Long id){
        ResponseEntity<List<Tarea>> miLista;
        miLista= new ResponseEntity<>(factoryTarea.mostrarTodasLasTareas(id), HttpStatus.OK);
        return miLista;
    }

    @PostMapping("/crear-tarea")
    public ResponseEntity<?> crearTarea(@Valid @RequestBody Tarea tarea, BindingResult result){
        ResponseEntity<Tarea> miTarea;
        ResponseEntity<List<Error>> errores;
        if (result.hasErrors()){
            List<Error> error=new ArrayList<>();
            result.getFieldErrors().forEach(err ->{
                error.add(new Error(err.getDefaultMessage()));
            });
            errores=new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            return errores;
        }
        miTarea=new ResponseEntity<>(factoryTarea.crearTarea(tarea),HttpStatus.OK);
        return miTarea;

    }

    @PutMapping("/actualizar-tarea/{id}")
    public ResponseEntity<?> actualizarTarea(@Valid @RequestBody Tarea tarea, BindingResult result, @PathVariable Long id){
        ResponseEntity<Tarea> miTarea;
        ResponseEntity<List<Error>> errores;
        if (result.hasErrors()){
            List<Error> error=new ArrayList<>();
            result.getFieldErrors().forEach(err ->{
                error.add(new Error(err.getDefaultMessage()));
            });
            errores=new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            return errores;
        }
        miTarea=new ResponseEntity<>(factoryTarea.actualizarTarea(id,tarea),HttpStatus.OK);
        return miTarea;
    }

    @DeleteMapping("/borrar-tarea/{id}")
    public ResponseEntity<?> borrarTarea(@PathVariable Long id){
        ResponseEntity<Tarea> miTarea;
        miTarea=new ResponseEntity<>(factoryTarea.borrarTarea(id),HttpStatus.OK);
        return miTarea;
    }




}
