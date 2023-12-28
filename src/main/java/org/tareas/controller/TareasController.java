package org.tareas.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tareas.model.Tarea;
import org.tareas.model.Usuario;
import org.tareas.repository.TareasRepository;
import org.tareas.repository.UsersRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TareasController {

    @Autowired
    TareasRepository tareasRepository;

    @Autowired
    UsersRepository usersRepository;

    @Operation(summary = "Listado de Tareas", description = "Listar Tareas")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/tareas")
    public ResponseEntity<List<Tarea>> getAllTasks(@RequestParam(required = false) Integer estado) {
        try {
            List<Tarea> tareas = new ArrayList<Tarea>();

            if (estado == null)
                tareas.addAll(tareasRepository.findAll());
            else
                tareas.addAll(tareasRepository.findByEstado(estado));

            if (tareas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tareas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar tarea por {Id}", description = "Busca una tarea dado un {Id} especifico")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/tareas/{id}")
    public ResponseEntity<Tarea> getTaskById(@PathVariable("id") long id) {
        Optional<Tarea> tareasData = tareasRepository.findById(id);

        return tareasData.map(tareas -> new ResponseEntity<>(tareas, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Crear tarea", description = "Crea una tarea asociada a un usuario y a un estado por su Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/tareas")
    public ResponseEntity<Tarea> createTasks(@RequestBody Tarea tarea) {
        try {
            Tarea _tarea = tareasRepository
                    .save(tarea);
            return new ResponseEntity<>(_tarea, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar tarea", description = "Actualiza la descripcion de una tarea segun su Id. Permite actualizar la descripcion, el usuario asignado y su estado.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/tareas/{id}")
    public ResponseEntity<Tarea> updateTasks(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Optional<Tarea> tareasData = tareasRepository.findById(id);

        if (tareasData.isPresent()) {
            Tarea _tareas = tareasData.get();
            _tareas.setDescripcion(tarea.getDescripcion());
            _tareas.setEstado(tarea.getEstado());
            _tareas.setUsuario(tarea.getUsuario());
            return new ResponseEntity<>(tareasRepository.save(_tareas), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Borrar tarea", description = "Borra una tarea segun su {Id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/tareas/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        try {
            tareasRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Borrar todas las tareas", description = "Borra todas las tareas")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/tareas")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        try {
            tareasRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Operation(summary = "Listado de Usuarios", description = "Lista todos los usuarios en la BD")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        try {

            List<Usuario> users = new ArrayList<Usuario>();

            users.addAll(usersRepository.findAll());


            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
