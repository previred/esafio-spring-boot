/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-12 19:34:24
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 20:00:21
 * @ Description:
 */

package spa.nuevo.desafiotecnico.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.model.EstadoTarea;
import spa.nuevo.desafiotecnico.service.EstadoTareaService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
@Api(tags = "Estados")
@ApiOperation(value = "Gestión de Estados")
public class EstadoController {
    private final EstadoTareaService estadoTareaService;

    @ApiOperation(value = "Lista todos los estados de tareas")
    @GetMapping()
    public List<EstadoTarea> listarTareas() {
        return estadoTareaService.findAll();
    }

    @ApiOperation(value = "Busca un estado por su id")
    @GetMapping("/{id}")
    public Optional<EstadoTarea> buscarTareaPorId(@PathVariable Integer id) {
        return estadoTareaService.findById(id);
    }

    @ApiOperation(value = "Crea o actualiza un estado")
    @PostMapping()
    public EstadoTarea crearTarea(@RequestBody EstadoTarea tarea) {
        return estadoTareaService.save(tarea);
    }

    @ApiOperation(value = "Elimina un estado por su id")
    @DeleteMapping("/{id}")
    public void eliminarEstado(@PathVariable Integer id) {
        estadoTareaService.delete(estadoTareaService.findById(id).get());
    }
}
