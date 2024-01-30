package cl.nuevo.spa.desafio.controller;

import cl.nuevo.spa.desafio.dto.TareaDTO;
import cl.nuevo.spa.desafio.model.Tarea;
import cl.nuevo.spa.desafio.service.TareaService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
@Api(tags = "Tareas")
@ApiOperation(value = "Gestión de Tareas")
public class TareaController {
    private final TareaService tareasService;

    @ApiOperation(value = "Lista todas las tareas")
    @GetMapping()
    public List<TareaDTO> listarTareas() {
        return tareasService.findAll();
    }

    @ApiOperation(value = "Busca una tarea por su id")
    @GetMapping("/{id}")
    public Optional<TareaDTO> buscarTareaPorId(@PathVariable Long id) {
        return tareasService.findById(id);
    }

    @ApiOperation(value = "Crea o actualiza una tarea")
    @PostMapping()
    public TareaDTO crearTarea(@RequestBody Tarea tarea) {
        return tareasService.save(tarea);
    }

    @ApiOperation(value = "Elimina una tarea por su id")
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareasService.delete(id);
    }
}
