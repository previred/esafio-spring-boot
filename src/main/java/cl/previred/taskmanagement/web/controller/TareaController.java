package cl.previred.taskmanagement.web.controller;



import cl.previred.taskmanagement.application.dto.request.ActualizarTareaRequest;
import cl.previred.taskmanagement.application.dto.request.CrearTareaRequest;
import cl.previred.taskmanagement.application.dto.response.RespuestaDTO;
import cl.previred.taskmanagement.core.service.TareaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarea")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<RespuestaDTO> crearTarea(@RequestBody @Valid CrearTareaRequest tareaRequest) {
        return new ResponseEntity<>(tareaService.crear(tareaRequest), HttpStatus.CREATED);
    }

    @GetMapping("/obtener-todas")
    public ResponseEntity<RespuestaDTO> obtenerTodas() {
        return new ResponseEntity<>(tareaService.obtenerTodas(), HttpStatus.OK);
    }

    @GetMapping("/obtener-por-usuario/{usuario}")
    public ResponseEntity<RespuestaDTO> obtenerPorUsuario(@PathVariable @NotNull @Size(min = 1) String usuario) {
        return new ResponseEntity<>(tareaService.obtenerTareasUsuario(usuario), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{codigo}")
    public ResponseEntity<RespuestaDTO> actualizar(@PathVariable @Pattern(regexp = "\\d+", message = "El código debe ser numérico") String codigo, @RequestBody @Valid ActualizarTareaRequest actualizarTareaRequest) {
        Long codigoLong = Long.parseLong(codigo);
        return new ResponseEntity<>(tareaService.actualizarTarea(codigoLong, actualizarTareaRequest), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<RespuestaDTO> eliminar(@PathVariable @Pattern(regexp = "\\d+", message = "El código debe ser numérico") String codigo) {
        Long codigoLong = Long.parseLong(codigo);
        return new ResponseEntity<>(tareaService.eliminarTarea(codigoLong), HttpStatus.OK);
    }
}
