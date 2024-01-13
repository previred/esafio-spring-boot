/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:17:28
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:38:50
 * @ Description:
 */

package spa.nuevo.desafiotecnico.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.dto.TareaDTO;
import spa.nuevo.desafiotecnico.model.Tarea;
import spa.nuevo.desafiotecnico.service.TareaService;

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
@RequestMapping("/tareas")
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
