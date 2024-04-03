package com.previred.desafioApiRest.desafioprevired.service.impl;


import com.previred.desafioApiRest.desafioprevired.exeptions.RequestException;
import com.previred.desafioApiRest.desafioprevired.repository.TareaRepository;
import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;
import com.previred.desafioApiRest.desafioprevired.service.TareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    /**
     * @param tarea Objeto del tipo Tarea
     * @return objeto de tipo Tarea guardada
     */
    @Override
    public Tarea save(Tarea tarea) {
        return Optional.ofNullable(tareaRepository.save(tarea))
                .orElseThrow(() -> new RequestException("Error al guardar tarea"));

    }

    /**
     * @param tarea Objeto del tipo Tarea
     * @return objeto de tipo Tarea actualizada
     */
    @Override
    public Tarea update(Tarea tarea) {
        Tarea tareaExistente = tareaRepository.findById(tarea.getId()).orElseThrow(() -> new NoSuchElementException("Tarea no encontrada"));

        tareaExistente.setEstadoTarea(tarea.getEstadoTarea());
        tareaExistente.setDescripcion(tarea.getDescripcion());
        tareaExistente.setUsuario(tarea.getUsuario());
        return Optional.ofNullable(tareaRepository.save(tarea))
                .orElseThrow(() -> new RequestException("Error al modificar tarea"));
    }

    /**
     * @return lista de objetos de tipo Tarea
     */
    @Override
    public List<Tarea> listTareas() {
        return Optional.ofNullable(tareaRepository.findAll())
                .orElseThrow(() -> new RequestException("Error al listar tareas"));
    }


    /**
     * @param idUsuario identificador del usuario de quien se realizara consulta de tareas
     * @return lista de objetos de tipo Tarea encontrado
     */
    @Override
    @Transactional
    public List<Tarea> listTareasByUuarioId(Long idUsuario) {
        return tareaRepository.listByUsuario(idUsuario).collect(Collectors.toList());

    }


    /**
     * @param id identificador de la Tarea de tipo Long
     * @return objeto de tipo Tarea encontrado
     */
    @Override
    public Tarea findById(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new RequestException("No se encuentra Tarea con id : " + id));
    }

    /**
     * @param id identificador de la tarea
     */
    @Override
    public void delete(Long id) {
        Tarea tarea = findById(id);
        Optional.ofNullable(tarea).ifPresent(t -> tareaRepository.delete(t));
    }

}
