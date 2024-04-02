package com.previred.nspa.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.previred.nspa.api.TareasApi;
import com.previred.nspa.desafio.util.TareasMapper;
import com.previred.nspa.entity.Tareas;
import com.previred.nspa.exception.OperacionFallidaException;
import com.previred.nspa.exception.RecursoNoEncontradoException;
import com.previred.nspa.model.TareasDTO;
import com.previred.nspa.service.TareasService;

@RestController
@RequestMapping("/tareas")
public class TareaApiImpController implements TareasApi {

    @Autowired
    private TareasService tareasService;

    
    @Override
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<List<TareasDTO>> tareasGet() {
        try {
            List<Tareas> tareas = tareasService.getAllTareas();
            List<TareasDTO> tareasDTOs = TareasMapper.INSTANCE.tareasToTareasDTOs(tareas);
            return ResponseEntity.ok(tareasDTOs);
        } catch (Exception ex) {
        	throw new OperacionFallidaException("Error al obtener todas las tareas", ex);
        }
    }

    
    @Override
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<Void> tareasIdDelete(@PathVariable("id") Integer id) {
        tareasService.deleteTarea(id);
        return ResponseEntity.noContent().build();
    }

    
    @Override
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<TareasDTO> tareasIdGet(@PathVariable("id") Integer id) {
        Tareas tarea = tareasService.getTareaById(id);
        if(tarea!=null) {
            TareasDTO tareaDTO = TareasMapper.INSTANCE.tareasToTareasDTO(tarea);
            return ResponseEntity.ok(tareaDTO);
        } else {
            throw new RecursoNoEncontradoException("Tarea no encontrada con ID: " + id);
        }
    }

    
    @Override
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<Void> tareasIdPut(@PathVariable("id") Integer id, @Valid @RequestBody TareasDTO tareasDTO) {
        Tareas tarea = TareasMapper.INSTANCE.tareasDTOToTareas(tareasDTO);
        tarea.setIdTarea(id);
        tareasService.saveTarea(tarea);
        return ResponseEntity.ok().build(); // Cambiado para reflejar una respuesta positiva consistente con la actualización exitosa.
    }

    
    @Override
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<Void> tareasPost(@Valid @RequestBody TareasDTO tareasDTO) {
        Tareas tarea = TareasMapper.INSTANCE.tareasDTOToTareas(tareasDTO);
        tareasService.saveTarea(tarea);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    
    @Override
    @PreAuthorize("isAuthenticated()") 
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }
}
