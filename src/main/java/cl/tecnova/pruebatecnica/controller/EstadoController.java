package cl.tecnova.pruebatecnica.controller;

import cl.tecnova.pruebatecnica.dto.EstadoDTO;
import cl.tecnova.pruebatecnica.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EstadoController implements EstadosApi {

    @Autowired
    private EstadosService estadosService;

    @Override
    public ResponseEntity<List<EstadoDTO>> estadosGet() {
        return new ResponseEntity<>(estadosService.getEstados(), HttpStatus.OK);
    }

}
