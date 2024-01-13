/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:08:35
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-11 21:35:19
 * @ Description:
 */

package spa.nuevo.desafiotecnico.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.model.Usuario;
import spa.nuevo.desafiotecnico.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<Usuario> listarUsuarios() {
        return usuarioService.findAll();
    }

    @ApiOperation(value = "Busca un usuario por su id")
    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @ApiOperation(value = "Crea un usuario")
    @PostMapping()
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @ApiOperation(value = "Elimina un usuario por su id")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.delete(usuarioService.findById(id).get());
    }
}
