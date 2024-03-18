package cl.nuevospa.gestortareas.controller;

import cl.nuevospa.gestortareas.api.UsuariosApi;
import cl.nuevospa.gestortareas.model.Usuarios;
import cl.nuevospa.gestortareas.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuariosController implements UsuariosApi {
    private final UsuariosService usuariosService;

    @Autowired
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    /**
     * Adds a new usuario to the system.
     *
     * @param usuarios the usuario object to be added
     * @return the response entity containing the result of the operation
     */
    @Override
    public ResponseEntity<Void> addUsuario(Usuarios usuarios) {
        return ResponseEntity.ok(usuariosService.addUsuario(usuarios));
    }

    /**
     * Deletes a usuario by the given id.
     *
     * @param id the id of the usuario to be deleted
     * @return the ResponseEntity with a status of OK and the result of deleting the usuario
     */
    @Override
    public ResponseEntity<Void> deleteUsuario(Integer id) {
        return ResponseEntity.ok(usuariosService.deleteUsuario(id));
    }

    /**
     * Retrieves all usuarios.
     *
     * @return Response entity with the list of usuarios
     */
    @Override
    public ResponseEntity<List<Usuarios>> getAllUsuarios() {
        return ResponseEntity.ok(usuariosService.findAllUsuarios());
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the ResponseEntity containing the user if found, or not found if not found
     */
    @Override
    public ResponseEntity<Usuarios> getUsuario(Integer id) {
        Optional<Usuarios> optionalUsuario = usuariosService.findUsuarioById(id);
        return optionalUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates a Usuario.
     *
     * @param id       the ID of the Usuario to update
     * @param usuarios the updated Usuario object
     * @return the ResponseEntity containing the updated Usuario
     */
    @Override
    public ResponseEntity<Void> updateUsuario(Integer id, Usuarios usuarios) {
        return ResponseEntity.ok(usuariosService.updateUsuario(id, usuarios));
    }
}
