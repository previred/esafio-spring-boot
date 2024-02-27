package com.nuevospa.controller;

import com.nuevospa.dto.UsuarioDTO;
import com.nuevospa.service.UsuarioServiceImpl;
import com.nuevospa.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@Api(value = "UsuarioController", tags = "API correspondiente a las acciones para los Usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@PostMapping("/generaToken")
	@ApiOperation(value = "Generar Token de Usuario", notes = "Endpoint para generar un token JWT para un usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Token generado exitosamente", response = String.class),
			@ApiResponse(code = 401, message = "Credenciales inválidas", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<String> generaTokenUsuario(@RequestBody UsuarioDTO usuarioDTO) {

		boolean esValido = usuarioService.validarUsuario(usuarioDTO);

		if (esValido) {
			// Generar token JWT si el usuario es válido
			String token = JwtUtil.generaToken(usuarioDTO.getNombre(), usuarioDTO.getPassword());
			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
		}
	}

	@PostMapping("/insertaUsuario")
	@ApiOperation(value = "Insertar Usuario", notes = "Endpoint para insertar un nuevo usuario")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Usuario ingresado exitosamente", response = String.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	private ResponseEntity<String> insertaUsuario(@RequestBody UsuarioDTO usuarioDTO,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {

				usuarioService.insertarUsuario(usuarioDTO);
				return new ResponseEntity<>("Se ingresó correctamente el usuario " + usuarioDTO.getNombre(),
						HttpStatus.OK);
			} catch (Exception e) {
				// Si hay un error, devuelve un mensaje de error
				String mensajeError = "Error al insertar el usuario. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/listaUsuario")
	@ApiOperation(value = "Lista de Usuarios", notes = "Endpoint para obtener la lista de usuarios")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación exitosa", response = UsuarioDTO.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "No existen registros", response = String.class) })
	public ResponseEntity<Object> listaUsuario(@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			List<UsuarioDTO> usuarios = usuarioService.listar();

			if (!usuarios.isEmpty()) {
				return ResponseEntity.ok(usuarios);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No existen usuarios");
			}

		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Eliminar Usuario por ID", notes = "Endpoint para eliminar un usuario por su ID")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Usuario eliminado exitosamente"),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> eliminaUsuarioPorId(@PathVariable Long id, @RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				usuarioService.eliminarUsuarioId(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				String mensajeError = "Error al eliminar el usuario. Detalles: " + e.getMessage();
				return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>("Token JWT no válido", HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/actualizaUsuario")
	@ApiOperation(value = "Actualizar Usuario", notes = "Endpoint para actualizar un usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación exitosa", response = UsuarioDTO.class),
			@ApiResponse(code = 404, message = "El usuario no fue encontrado", response = String.class),
			@ApiResponse(code = 401, message = "Token JWT no válido", response = String.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = String.class) })
	public ResponseEntity<?> actualizaUsuario(@RequestBody UsuarioDTO usuarioDTO,
			@RequestHeader("Authorization") String token) {

		if (JwtUtil.tokenExpirado(token)) {
			try {
				Optional<UsuarioDTO> salida = usuarioService.actualizarUsuario(usuarioDTO);

				if (salida.isPresent()) {
					UsuarioDTO usuarioActualizado = salida.get();
					return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
				} else {
					// El Optional está vacío (es nulo)
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
