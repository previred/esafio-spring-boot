package cl.nuevo.spa.desafio.controller;

import cl.nuevo.spa.desafio.dto.UsuarioDTO;
import cl.nuevo.spa.desafio.model.Usuario;
import cl.nuevo.spa.desafio.service.UsuarioService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = "Usuarios")
@ApiOperation(value = "Gestión de Usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @ApiOperation(value = "Lista todos los usuarios")
    @GetMapping()
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.findAll();
    }

    @ApiOperation(value = "Busca un usuario por su id")
    @GetMapping("/{id}")
    public Optional<UsuarioDTO> buscarUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @ApiOperation(value = "Crea o actualiza un usuario")
    @PostMapping()
    public UsuarioDTO crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @ApiOperation(value = "Elimina un usuario por su id")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}
