package com.previred.controller;

import com.previred.dto.Response;
import com.previred.dto.ResponseError;
import com.previred.model.entitys.Tarea;
import com.previred.model.service.TareaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Servicios API Previred",version = "1.0.0"))
@RestController
@ApiResponse(responseCode = "200",description = "OK",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Response.class))
})
@ApiResponse(responseCode = "400",description = "La solicitud es incorrecta",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseError.class))
})
@ApiResponse(responseCode = "401",description = "Solicitud no autorizada",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseError.class))
})
@ApiResponse(responseCode = "500",description = "Error servidor",content = {
        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ResponseError.class))
})
@RequestMapping("api")
public class ApiRestController {

    @Autowired
    protected TareaService tareaService;

    @Operation(summary = "Permite mostrar una tarea por su ID",description = "Muestra el detalle de la tarea, estado y el usuario asignado")
    @GetMapping(value = "mostrarTarea",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> mostrarTarea(@RequestParam(name = "id") Long id){
        return tareaService.mostrarTarea(id);
    }

    @Operation(summary = "Permite agregar una nueva tarea",description = "Se debe ingresar los parametros de la tarea, ID usuario, ID tarea.")
    @PostMapping (value = "agregarTarea", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> agregarTarea(@RequestBody Tarea tarea){
        return tareaService.agregarTarea(tarea);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @Operation(summary = "Permite agregar 1 o varias tareas")
    @PostMapping (value = "agregarTareas", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> agregarTareas(@RequestBody List<Tarea> tareas){
        return tareaService.agregarTareas(tareas);
    }

    @Operation(summary = "Permite actualizar una tarea existente",description = "Recibe los parametros de la tarea incluyendo el ID")
    @PutMapping(value = "actualizarTarea",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarTarea(@RequestBody Tarea tarea){
        return tareaService.actualizarTarea(tarea);
    }

    @Operation(summary = "Permite eliminar una tarea por su ID")
    @DeleteMapping(value = "eliminarTarea",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarTarea(@RequestParam Long id){
        return tareaService.eliminarTarea(id);
    }
}
