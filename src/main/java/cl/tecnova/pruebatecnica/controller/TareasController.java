package cl.tecnova.pruebatecnica.controller;

import cl.tecnova.pruebatecnica.dto.TareaDTO;
import cl.tecnova.pruebatecnica.dto.UpdateTareaRequest;
import cl.tecnova.pruebatecnica.exception.HTTPException;
import cl.tecnova.pruebatecnica.services.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TareasController implements TareasApi {

    @Autowired
    private TareasService tareasService;

    @Override
    public ResponseEntity<TareaDTO> createTarea(@RequestBody UpdateTareaRequest request) throws HTTPException {
        return new ResponseEntity<>(tareasService.createTarea(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TareaDTO>> getAllTareas() {
        return new ResponseEntity<>(tareasService.getAllTareas(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TareaDTO> getTarea(@PathVariable("id") Integer id) throws HTTPException {
        return new ResponseEntity<>(tareasService.getTarea(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TareaDTO> updateTarea(@PathVariable("id") Integer id, @RequestBody UpdateTareaRequest request) throws HTTPException {
        return new ResponseEntity<>(tareasService.updateTarea(id, request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteTarea(@PathVariable("id") Integer id) throws HTTPException {
        tareasService.eliminarTarea(id);
        return new ResponseEntity<>("Tarea ID: " + id + " eliminada con exito.", HttpStatus.OK);
    }

}
