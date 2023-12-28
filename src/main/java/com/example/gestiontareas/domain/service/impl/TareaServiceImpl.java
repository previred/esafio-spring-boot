package com.example.gestiontareas.domain.service.impl;

import com.example.gestiontareas.domain.model.EstadoTarea;
import com.example.gestiontareas.domain.model.Tarea;
import com.example.gestiontareas.domain.model.Usuario;
import com.example.gestiontareas.domain.repository.EstadoTareaRepository;
import com.example.gestiontareas.domain.repository.TareaRepository;
import com.example.gestiontareas.domain.repository.UsuarioRepository;
import com.example.gestiontareas.domain.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements ITareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoTareaRepository estadoTareaRepository;

    @Autowired
    public TareaServiceImpl(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, EstadoTareaRepository estadoTareaRepository){
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoTareaRepository = estadoTareaRepository;
    }

    @Override
    public List<Tarea> findAll() {
        return (List<Tarea>) tareaRepository.findAll();
    }

    @Override
    public Tarea findById(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    @Override
    public Tarea asignarTarea(Long tareaId, Long usuarioId) {

        Tarea tarea = tareaRepository.findById(tareaId).orElse(null);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (!ObjectUtils.isEmpty(tarea) && !ObjectUtils.isEmpty(usuario)){
            tarea.setUsuario(usuario);
            EstadoTarea estadoTarea = estadoTareaRepository.findById(2L).orElse(null);
            tarea.setEstadoTarea(estadoTarea);
            tarea.setAsigned(LocalDateTime.now());
            tareaRepository.save(tarea);
            return tarea;
        }
        return null;
    }
    @Override
    public Tarea create(Tarea tarea) {

        EstadoTarea estadoTarea = estadoTareaRepository.findById(1L).orElse(null);
        tarea.setEstadoTarea(estadoTarea);
        return tareaRepository.save(tarea);
    }

    @Override
    public void deleteById(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public List<Tarea> findByUserId(Long id) {
        List<Tarea> tareasList = (List<Tarea>) tareaRepository.findAll();
        if (ObjectUtils.isEmpty(tareasList)){
            return tareasList;
        }
        return tareasList.stream().filter(e -> {
            Usuario usuario = e.getUsuario();
            return (!ObjectUtils.isEmpty(usuario) && usuario.getId() == id);
        }).collect(Collectors.toList());
    }

    @Override
    public Tarea finalizar(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        EstadoTarea estadoTarea = estadoTareaRepository.findById(3L).orElse(null);
        //No se va a finalizar una tarea que no tenga fecha de asignación
        if (ObjectUtils.isEmpty(tarea) || ObjectUtils.isEmpty(estadoTarea) || ObjectUtils.isEmpty(tarea.getAsigned())){
            return null;
        }
        tarea.setEstadoTarea(estadoTarea);
        tarea.setFinished(LocalDateTime.now());
        return tareaRepository.save(tarea);
    }

}
