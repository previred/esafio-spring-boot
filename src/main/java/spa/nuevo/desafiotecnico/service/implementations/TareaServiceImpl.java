/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 21:37:51
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:50:54
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service.implementations;

import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.dto.TareaDTO;
import spa.nuevo.desafiotecnico.dto.UsuarioDTO;
import spa.nuevo.desafiotecnico.model.Tarea;
import spa.nuevo.desafiotecnico.repository.TareaRepository;
import spa.nuevo.desafiotecnico.service.TareaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;

    @Override
    public List<TareaDTO> findAll() {
        List<Tarea> tareas = tareaRepository.findAll();

        return tareas.stream()
                .map(tarea -> new TareaDTO(
                        tarea.getId(),
                        tarea.getNombre(),
                        tarea.getDescripcion(),
                        tarea.getEstadoTarea().getEstado(),
                        tarea.getCreatedAt(),
                        new UsuarioDTO(
                                tarea.getUsuario().getId(),
                                tarea.getUsuario().getUsername(),
                                tarea.getUsuario().getEmail())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TareaDTO> findById(Long id) {
        return tareaRepository.findById(id)
                .map(tarea -> new TareaDTO(
                        tarea.getId(),
                        tarea.getNombre(),
                        tarea.getDescripcion(),
                        tarea.getEstadoTarea().getEstado(),
                        tarea.getCreatedAt(),
                        new UsuarioDTO(
                                tarea.getUsuario().getId(),
                                tarea.getUsuario().getUsername(),
                                tarea.getUsuario().getEmail())));
    }

    @Override
    public TareaDTO save(Tarea tarea) {
        if (tarea.getId() == null) {
            Optional<Long> maxID = tareaRepository.findAll()
                    .stream().map(Tarea::getId)
                    .max(Long::compare);
            tarea.setId(maxID.orElse(0L) + 1);
        }
        tarea = tareaRepository.save(tarea);
        return new TareaDTO(
                tarea.getId(),
                tarea.getNombre(),
                tarea.getDescripcion(),
                tarea.getEstadoTarea().getEstado(),
                tarea.getCreatedAt(),
                new UsuarioDTO(
                        tarea.getUsuario().getId(),
                        tarea.getUsuario().getUsername(),
                        tarea.getUsuario().getEmail()));
    }

    @Override
    public void delete(Long id) {
        tareaRepository.deleteById(id);
    }

}
