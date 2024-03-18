package cl.nuevospa.gestortareas.controller;

import cl.nuevospa.gestortareas.api.TareasApi;
import cl.nuevospa.gestortareas.model.Tareas;
import cl.nuevospa.gestortareas.service.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class TareasController implements TareasApi {
    private final TareasService tareasService;

    @Autowired
    public TareasController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    /**
     * Adds a Tarea to the system.
     *
     * @param tareas the Tareas object to add
     * @return the ResponseEntity containing the added Tareas
     */
    @Override
    public ResponseEntity<Void> addTarea(Tareas tareas) {
        return ResponseEntity.ok(tareasService.addTarea(tareas));
    }

    /**
     * Deletes a Tarea by ID.
     *
     * @param id the ID of the Tarea to delete
     * @return the ResponseEntity with the result of the deletion
     */
    @Override
    public ResponseEntity<Void> deleteTarea(Integer id) {
        return ResponseEntity.ok(tareasService.deleteTarea(id));
    }

    /**
     * Find Tareas by Estado.
     *
     * @param idEstado the id of the Estado
     * @return list of Tareas found by Estado
     */
    @Override
    public ResponseEntity<List<Tareas>> findTareasByEstado(Integer idEstado) {
        return ResponseEntity.ok(tareasService.findTareasByEstado(idEstado));
    }

    /**
     * Retrieves a Tareas object by its ID.
     *
     * @param id the ID of the Tareas object to retrieve
     * @return the ResponseEntity containing the Tareas object, or a 404 Not Found response if the object does not exist
     */
    @Override
    public ResponseEntity<Tareas> getTarea(Integer id) {
        Optional<Tareas> tareasOptional = tareasService.findTareaById(id);
        return tareasOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * A method to list all Tareas.
     *
     * @return Response entity with a list of Tareas
     */
    @Override
    public ResponseEntity<List<Tareas>> listAllTareas() {
        return ResponseEntity.ok(tareasService.findAllTareas());
    }

    /**
     * Update a Tarea.
     *
     * @param id     description of parameter
     * @param tareas description of parameter
     * @return description of return value
     */
    @Override
    public ResponseEntity<Void> updateTarea(Integer id, Tareas tareas) {
        return ResponseEntity.ok(tareasService.updateTarea(id, tareas));
    }
}
