/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:08:35
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:53:04
 * @ Description:
 */

package spa.nuevo.desafiotecnico.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.dto.UsuarioDTO;
import spa.nuevo.desafiotecnico.model.Usuario;
import spa.nuevo.desafiotecnico.service.UsuarioService;

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
@RequestMapping("/usuarios")
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
