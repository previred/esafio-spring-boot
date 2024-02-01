package com.desafio.spring.desafiospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.spring.desafiospringboot.model.Tarea;
import com.desafio.spring.desafiospringboot.service.TareaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TareaController {
	
	@Autowired
	private TareaService tareaService;
	
	@Operation( summary = "Nos permite agregar una nueva tarea", description = "Puede usar estos valores en el RequestBody: nombreTarea: 'Tarea 1', descripcionTarea: 'Descripcion Tarea 1'",
				parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
				tags = {"Servicios"} )
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
  							content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
  							content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@PostMapping("/nuevaTarea")
    public ResponseEntity<?> nuevaTarea(@RequestBody Tarea tarea) {
        return tareaService.guardarTarea(tarea);
    }
	
	@Operation( summary = "Nos permite ver una tarea", description = "Aqui podemos ver una tarea por su identificador.\n\nSi conocemos el Identficador, podemos obtener todas las tareas que existen en BD con el servicio /obtenerTareas,\n\nsi no existen aún, debe crearlas con el servicio /nuevaTarea",
			parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
			tags = {"Servicios"} )
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
								content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
								content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@GetMapping("/verTarea/{idTarea}")
    public ResponseEntity<?> verTarea(@PathVariable Long idTarea) {
        return tareaService.verTarea(idTarea);
    }
	
	@Operation( summary = "Nos permite modificar una tarea", description = "Aqui podemos modificar descripcion de una tarea\n\n"
						+ "Puede usar estos valores en el RequestBody: id_tarea: 1, descripcionTarea: 'Descripcion Modificada'",
			parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
			tags = {"Servicios"} )
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
								content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
								content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@PostMapping("/modificarTarea")
    public ResponseEntity<?> modificarTarea(@RequestBody Tarea tarea) {
        return tareaService.modificarTarea(tarea);
    }
	
	@Operation( summary = "Nos permite poner en estado TERMINADA una tarea por su identificador", description = "Aqui podemos poner en estado TERMINADA una tarea por su identificador.\n\nSi conocemos el Identficador, podemos obtener todas las tareas que existen en BD con el servicio /obtenerTareas,\n\nsi no existen aún, debe crearlas con el servicio /nuevaTarea",
			parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
			tags = {"Servicios"})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
								content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
								content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@GetMapping("/terminarTarea/{idTarea}")
    public ResponseEntity<?> terminarTarea(@PathVariable Long idTarea) {
        return tareaService.terminarTarea(idTarea);
    }
	
	@Operation( summary = "Nos permite eliminar una tarea por su identificador", description = "Aqui podemos ELIMINAR una tarea por su identificador.\n\nSi conocemos el Identficador, podemos obtener todas las tareas que existen en BD con el servicio /obtenerTareas,\n\nsi no existen aún, debe crearlas con el servicio /nuevaTarea",
			parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
			tags = {"Servicios"} )
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
								content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
								content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@PutMapping("/eliminarTarea/{idTarea}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long idTarea) {
        return tareaService.eliminarTarea(idTarea);
    }
	
	@Operation( summary = "Nos permite obtener las tareas desde la BD", description = "Nos permite obtener las tareas desde la BD,\n\nsi no existen aún, debe crearlas con el servicio /nuevaTarea",
			parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
			tags = {"Servicios"} )
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
								content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
								content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@GetMapping("/obtenerTareas")
	public ResponseEntity<?> obtenerTareas() {
		return tareaService.obtenerTareas();
	}

}
