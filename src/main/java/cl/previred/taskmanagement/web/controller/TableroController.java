package cl.previred.taskmanagement.web.controller;

import cl.previred.taskmanagement.application.dto.request.CrearTableroRequest;
import cl.previred.taskmanagement.application.dto.response.RespuestaTableroDTO;
import cl.previred.taskmanagement.core.service.TableroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tablero")
public class TableroController {

    private final TableroService tableroService;

    public TableroController(TableroService tableroService){
        this.tableroService = tableroService;
    }

    @PostMapping("/crear")
    public ResponseEntity<RespuestaTableroDTO> crearTablero(@RequestBody @Valid CrearTableroRequest request){
        return new ResponseEntity<>(tableroService.crearTablero(request), HttpStatus.CREATED);
    }

    @GetMapping("/obtener-por-codigo/{codigo}")
    public ResponseEntity<RespuestaTableroDTO> obtenerPorCodigo(@PathVariable String codigo){
        return new ResponseEntity<>(tableroService.buscarTableroPorCodigo(codigo), HttpStatus.OK);
    }
}
