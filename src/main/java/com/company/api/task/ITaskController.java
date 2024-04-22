package com.company.api.task;

import com.company.model.Task;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ITaskController {


    @ApiOperation(value = "Servicio que permite crear una tarea ",
                  notes = "El servicio retorna un 204, no retorna contenido en caso de exito",
                  nickname = "create",
                  response = Void.class)
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody final Task task);


    @ApiOperation(value = "Servicio que permite actualizar una tarea ",
            notes =  "El servicio retorna un 204, no retorna contenido en caso de exito",
            nickname = "update",
            response = Void.class)
    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody final Task task);


    @ApiOperation(value = "Servicio que permite eliminar una tarea ",
            notes =  "El servicio retorna un 204, no retorna contenido en caso de exito",
            nickname = "delete",
            response = Void.class)
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID uuid);


    @ApiOperation(value = "Servicio que permite obtener una tarea por ID",
            notes =  "Retorna un valor nulo en caso de existir el identificador de la tarea",
            nickname = "findById",
            response = Task.class)
    @GetMapping(value = "find/{id}")
    public ResponseEntity<Optional<Task>> findById(@PathVariable("id") UUID uuid);

    @ApiOperation(value = "Servicio que permite obtener todas las tareas",
            notes =  "Retorna una lista vacia en caso de no existir tareas registradas",
            nickname = "delete",
            response = Task[].class)
    @GetMapping
    public ResponseEntity<List<Task>> getAll();
}
