package com.example.desafio.controller;

import com.example.desafio.model.entities.Tarea;
import com.example.desafio.model.response.ListadoTareasResponse;
import com.example.desafio.service.TareaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping(path = "/tareas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TareaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TareaController.class);
    @Autowired
    TareaService tareaService;
    @ApiOperation("Lista de tareas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se muestra la lista de tareas correctamente"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Sin autorizacion"),
            @ApiResponse(code = 500, message = "Error interno servidor")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<ListadoTareasResponse>> getTareas() {
        return new ResponseEntity<>(tareaService.listar(), HttpStatus.OK);
    }

    @ApiOperation("Crea una tarea")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se crea una tarea correctamente"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Sin autorizacion"),
            @ApiResponse(code = 500, message = "Error interno servidor")
    })
    @PostMapping("/crear")
    public ResponseEntity<String> createTarea(@RequestBody Tarea tarea){
        tareaService.crearTarea(tarea);

        return new ResponseEntity<>("Tarea creada exitósamente", HttpStatus.OK);
    }
    @ApiOperation("Actualiza una tarea")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se actualiza una tarea correctamente"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Sin autorizacion"),
            @ApiResponse(code = 500, message = "Error interno servidor")
    })
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Tarea tarea) {
        tareaService.actualizarTarea(tarea);
        return new ResponseEntity<>("Tarea actualizada correctamente", HttpStatus.OK);
    }
    @ApiOperation("Elimina una tarea")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se elimina una tarea correctamente"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Sin autorizacion"),
            @ApiResponse(code = 500, message = "Error interno servidor")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrar(@PathVariable("id") Integer id) {
        tareaService.eliminarTarea(id);

        return new ResponseEntity<>("Tarea eliminada exitósamente", HttpStatus.OK);
    }
}
