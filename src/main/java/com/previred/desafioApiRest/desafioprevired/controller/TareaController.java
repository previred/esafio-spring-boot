package com.previred.desafioApiRest.desafioprevired.controller;

import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;
import com.previred.desafioApiRest.desafioprevired.service.TareaService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    private static final Log LOGGER = LogFactory.getLog("TareaController" + TareaController.class);


    /**
     * @param tarea objeto de tipo Tarea
     * @return objeto de tipo Tarea guardado
     */
    @ApiOperation(value = "Guarda objeto de tipo tarea", notes = "Guarda objeto de tipo tarea", response = Tarea.class, responseContainer = "Object")
    @PostMapping(path = "/save",consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public Tarea save(@RequestBody Tarea tarea) {
        LOGGER.info("*****INICIO PETICION CREAR TAREA*****");
        return tareaService.save(tarea);
    }


    /**
     * @param tarea objeto de tipo Tarea
     * @return objeto de tipo Tarea modifica
     */
    @ApiOperation(value = "Modifica objeto de tipo tarea", notes = "Modifica objeto de tipo tarea", response = Tarea.class, responseContainer = "Object")
    @PostMapping(path = "/update",consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public Tarea update(@RequestBody Tarea tarea) {
        LOGGER.info("*****INICIO PETICION MODIFICAR TAREA*****");
        return tareaService.update(tarea);
    }


    /**
     * @param id Identificador de la tarea
     * @return objeto de tipo Tarea encontrando
     */
    @ApiOperation(value = "Buscar una tarea", notes = "Buscar una tarea de acuerdo a un identificador", response = Tarea.class, responseContainer = "Object")
    @GetMapping("{id}")
    public Tarea findById(@PathVariable("id") Long id) {
        LOGGER.info("*****INICIO PETICION BUSCAR TAREA POR ID*****");
        return tareaService.findById(id);
    }


    /**
     * @return Lista de objetos de tipo Tarea
     */
    @ApiOperation(value = "Listar tareas", notes = "Listar todas las tareas existentes", response = Tarea.class, responseContainer = "List")
    @GetMapping("list")
    public List<Tarea> listTareas() {
        LOGGER.info("*****INICIO PETICION LISTAR TAREAS*****");
        return tareaService.listTareas();
    }


    /**
     * @param idUsuario Identificador de usuario
     * @return Lista de objetos de tipo Tarea
     */
    @ApiOperation(value = "Listar todas las tareas de un usuario", notes = "listar todas las tareas de un usuario en particular", response = Tarea.class, responseContainer = "List")
        @GetMapping("list-tarea-by-id/{idUsuario}")
    public List<Tarea> listTareasByUuarioId(@PathVariable("idUsuario") Long idUsuario) {
        LOGGER.info("*****INICIO PETICION LISTAR TAREAS POR ID USUARIO*****");
        return tareaService.listTareasByUuarioId(idUsuario);
    }


    /**
     * @param id Identificador de la tarea
     */

    @ApiOperation(value = "Elimina una tarea", notes = "Elimina una tarea de acuerdo a un identificador")
    @DeleteMapping("{id}")
    public void deteleById(@PathVariable("id") Long id) {
        LOGGER.info("*****INICIO PETICION ELIMINAR TAREA*****");
        tareaService.delete(id);
    }
}
