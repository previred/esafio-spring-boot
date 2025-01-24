package cl.gestion.tarea.controller;

import cl.gestion.tarea.model.Tarea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
@Slf4j
public class TareaController {


    @Autowired
    private TareaRepository tareaRepository;

    @GetMapping
    public List<Tarea> getAllTareas() {
        List<Tarea> tarea;
        tarea = tareaRepository.findAll();
        return tarea;
    }

    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("{ \"respuesta\" : \"Estoy vivo !!!\"}", HttpStatus.OK);
    }

    @PostMapping
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @RequestBody Tarea updatedTarea) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTitle(updatedTarea.getTitle());
                    tarea.setDescription(updatedTarea.getDescription());
                    return ResponseEntity.ok(tareaRepository.save(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
