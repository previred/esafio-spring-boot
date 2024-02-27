package com.nuevospa.controller;

import com.nuevospa.dto.EstadoTareaDTO;
import com.nuevospa.entity.EstadoTareaEntity;
import com.nuevospa.interfaces.EstadoTareaService;
import com.nuevospa.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estadotarea")
@Api(value = "EstadoTareaController", tags = "API correspondiente a las acciones para los Estados de Tareas")
public class EstadoTareaController {

	@Autowired
	private EstadoTareaService estadoTareaService;

	@PostMapping("/insertaEstadoTarea")
	@ApiOperation(value = "Insertar Estado de Tarea", notes = "Endpoint para insertar un nuevo estado de tarea")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Estado de Tarea creado exitosamente", response = EstadoTareaEntity.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> insertaEstadoTarea(
			@ApiParam(value = "Nombre del estado de la tarea a insertar", required = true) @RequestBody String nombre,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				EstadoTareaEntity estadoTarea = estadoTareaService.guardarEstadoTarea(nombre);
				return new ResponseEntity<>(estadoTarea, HttpStatus.CREATED);
			} catch (Exception e) {
				String mensajeError = "Error al guardar el estado de tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/listaEstadosTarea")
	@ApiOperation(value = "Obtener Lista de Estados de Tarea", notes = "Endpoint para obtener todos los estados de tarea")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa", response = List.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> listaEstadosTarea(@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				List<EstadoTareaDTO> estadosTarea = estadoTareaService.obtenerTodosEstadosTarea();

				if (!estadosTarea.isEmpty()) {
					return ResponseEntity.ok(estadosTarea);
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No existen estados de tareas");
				}

			} catch (Exception e) {
				String mensajeError = "Error al obtener todos los estados de tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/obtieneEstadoTareaPorId/{id}")
	@ApiOperation(value = "Obtener Estado de Tarea por ID", notes = "Endpoint para obtener el estado de tarea por su ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa", response = Optional.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> obtieneEstadoTareaPorId(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				Optional<Object> estadoTarea = estadoTareaService.obtenerEstadoTareaPorId(id);
				return new ResponseEntity<>(estadoTarea, HttpStatus.OK);
			} catch (Exception e) {
				String mensajeError = "Error al obtener el estado de tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/eliminaEstadoTareaPorId/{id}")
	@ApiOperation(value = "Eliminar Estado de Tarea por ID", notes = "Endpoint para eliminar el estado de tarea por su ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa", response = String.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> eliminaEstadoTareaPorId(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				estadoTareaService.eliminarEstadoTareaPorId(id);
				return new ResponseEntity<>("Se eliminó el estado de tarea " + id + " ", HttpStatus.OK);
			} catch (Exception e) {
				String mensajeError = "Error al eliminar el estado de tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/actualizaEstadosTarea")
	@ApiOperation(value = "Actualizar Estado de Tarea", notes = "Endpoint para actualizar el estado de una tarea")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "estadoTareaDTO", value = "Objeto EstadoTareaDTO", required = true, dataType = "EstadoTareaDTO", paramType = "body", defaultValue = "{ \"id\": 1, \"nombre\": \"Tarea 1\", \"estado\": \"COMPLETADA\" }") })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación exitosa", response = EstadoTareaDTO.class, examples = @Example(@ExampleProperty(value = "{ \"id\": 1, \"nombre\": \"Tarea 1\", \"estado\": \"COMPLETADA\" }"))),
			@ApiResponse(code = 404, message = "El estado de tarea no fue encontrado", response = String.class, examples = @Example(@ExampleProperty(value = "El estado de tarea no fue encontrado"))),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class, examples = @Example(@ExampleProperty(value = "Error interno del servidor"))) })
	public ResponseEntity<?> actualizarEstadoTarea(@RequestBody EstadoTareaDTO estadoTareaDTO,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				Optional<EstadoTareaDTO> salida = estadoTareaService.actualizarEstadoTarea(estadoTareaDTO);

				if (salida.isPresent()) {

					EstadoTareaDTO estadoTareaActualizado = salida.get();
					return new ResponseEntity<>(estadoTareaActualizado, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("El usuario no fue encontrado", HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				String mensajeError = "Error al actualizar el usuario. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

}
