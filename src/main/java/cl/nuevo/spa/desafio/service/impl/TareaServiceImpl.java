package cl.nuevo.spa.desafio.service.impl;

import cl.nuevo.spa.desafio.dto.TareaDTO;
import cl.nuevo.spa.desafio.dto.UsuarioDTO;
import cl.nuevo.spa.desafio.model.Tarea;
import cl.nuevo.spa.desafio.repository.TareaRepository;
import cl.nuevo.spa.desafio.service.TareaService;
import lombok.AllArgsConstructor;

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
                        tarea.getEstadoTarea().getEstado(),
                        tarea.getCreatedAt(),
                        new UsuarioDTO(
                                tarea.getUsuario().getId(),
                                tarea.getUsuario().getUsername(),
                                tarea.getUsuario().getEmail())))
                .toList();
    }

    @Override
    public Optional<TareaDTO> findById(Long id) {
        return tareaRepository.findById(id)
                .map(tarea -> new TareaDTO(
                        tarea.getId(),
                        tarea.getEstadoTarea().getEstado(),
                        tarea.getCreatedAt(),
                        new UsuarioDTO(
                                tarea.getUsuario().getId(),
                                tarea.getUsuario().getUsername(),
                                tarea.getUsuario().getEmail())));
    }

    @Override
    public TareaDTO save(Tarea tarea) {
        tarea = tareaRepository.save(tarea);
        return new TareaDTO(
                tarea.getId(),
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
