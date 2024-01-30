package cl.nuevo.spa.desafio.controller;

import cl.nuevo.spa.desafio.model.EstadoTarea;
import cl.nuevo.spa.desafio.service.EstadoTareaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/status")
@Api(tags = "Estados")
@ApiOperation(value = "Gestión de Estados")
public class EstadoController {
    private final EstadoTareaService estadoTareaService;

    @ApiOperation(value = "Lista todos los estados de tareas")
    @GetMapping()
    public List<EstadoTarea> listarTareas() {
        return estadoTareaService.findAll();
    }

    @ApiOperation(value = "Buscar un estado filtrando por id")
    @GetMapping("/{id}")
    public Optional<EstadoTarea> buscarTareaPorId(@PathVariable Integer id) {
        return estadoTareaService.findById(id);
    }

    @ApiOperation(value = "Crea un estado")
    @PostMapping()
    public EstadoTarea crearTarea(@RequestBody EstadoTarea tarea) {
        return estadoTareaService.save(tarea);
    }

    @ApiOperation(value = "Actualiza un estado")
    @PutMapping()
    public EstadoTarea actualizarTarea(@RequestBody EstadoTarea tarea) {
        return estadoTareaService.save(tarea);
    }

    @ApiOperation(value = "Elimina un estado por su id")
    @DeleteMapping("/{id}")
    public void eliminarEstado(@PathVariable Integer id) {
        estadoTareaService.delete(estadoTareaService.findById(id).get());
    }
}
