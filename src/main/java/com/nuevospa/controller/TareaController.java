package com.nuevospa.controller;

import com.nuevospa.dto.TareaDTO;
import com.nuevospa.dto.TareaIdDTO;
import com.nuevospa.entity.EstadoTareaEntity;
import com.nuevospa.interfaces.EstadoTareaService;
import com.nuevospa.interfaces.TareaService;
import com.nuevospa.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarea")
@Api(value = "TareaController", tags = "API correspondiente a las acciones para las Tareas")
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@Autowired
	private EstadoTareaService estadoTareaService;

	@PostMapping("/insertaTarea")
	@ApiOperation(value = "Insertar Tarea", notes = "Endpoint para insertar una nueva tarea")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tarea creada exitosamente", response = TareaDTO.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> insertaTarea(
			@ApiParam(value = "Datos de la tarea a insertar", required = true) @RequestBody TareaDTO tareaDTO,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				EstadoTareaEntity estadoTarea = estadoTareaService
						.obtenerEstadoTareaEntityPorId(tareaDTO.getIdEstadoTarea());

				if (estadoTarea == null) {
					return new ResponseEntity<>(
							"No se encontró el Estado de la tarea, para ser asociado a la nueva Tarea",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

				TareaDTO tareaGuardada = tareaService.guardarTarea(tareaDTO, estadoTarea);
				return new ResponseEntity<>(tareaGuardada, HttpStatus.CREATED);
			} catch (Exception e) {
				String mensajeError = "No se pudo guardar la tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/listaTarea")
	@ApiOperation(value = "Obtener Lista de Tareas", notes = "Endpoint para obtener la lista de todas las tareas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación exitosa", response = TareaDTO.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> listaTarea(@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				List<TareaDTO> tareas = tareaService.obtenerTareas();

				if (!tareas.isEmpty()) {
					return ResponseEntity.ok(tareas);
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No existen tareas");
				}

			} catch (Exception e) {
				String mensajeError = "Error al obtener las tareas. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/obtienePorId/{id}")
	@ApiOperation(value = "Obtener Tarea por ID", notes = "Endpoint para obtener una tarea por su ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa", response = TareaDTO.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 404, message = "Tarea no encontrada", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> obtieneTareaPorId(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {

			try {
				Optional<TareaDTO> tarea = tareaService.obtenerTareaPorId(id);

				if (tarea.isPresent()) {
					return new ResponseEntity<>(tarea, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Tarea no encontrada", HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				String mensajeError = "Error al obtener la tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/eliminaTareaPorId/{id}")
	@ApiOperation(value = "Eliminar Tarea por ID", notes = "Endpoint para eliminar una tarea por su ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarea eliminada exitosamente", response = String.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> eliminaTareaPorId(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {

			try {
				tareaService.eliminarTareaPorId(id);
				return new ResponseEntity<>("Tarea eliminada exitosamente", HttpStatus.OK);
			} catch (Exception e) {
				String mensajeError = "Error al eliminar la tarea o no fue encontrada.";
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/actualizaTarea")
	@ApiOperation(value = "Actualizar Tarea", notes = "Endpoint para actualizar una tarea")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa", response = TareaDTO.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 404, message = "La tarea no fue encontrada", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> actualizaTarea(@RequestBody TareaIdDTO tareaDTO,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {

				EstadoTareaEntity estadoTarea = estadoTareaService
						.obtenerEstadoTareaEntityPorId(tareaDTO.getIdEstadoTarea());

				if (estadoTarea == null) {
					return new ResponseEntity<>(
							"No se encontró el Estado de la tarea, para ser asociado a la nueva Tarea",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

				Optional<TareaDTO> salida = tareaService.actualizarTarea(tareaDTO, estadoTarea);

				if (salida.isPresent()) {
 
					
					TareaDTO tareaActualizado = salida.get();

					return new ResponseEntity<>(tareaActualizado, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("La tarea no fue encontrada", HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				String mensajeError = "Error al actualizar la tarea. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

}
