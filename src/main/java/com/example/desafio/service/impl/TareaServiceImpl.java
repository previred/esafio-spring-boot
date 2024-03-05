package com.example.desafio.service.impl;

import com.example.desafio.model.entities.Tarea;
import com.example.desafio.model.response.ListadoTareasResponse;
import com.example.desafio.repository.TareaRepository;
import com.example.desafio.service.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TareaServiceImpl implements TareaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TareaServiceImpl.class);
    @Autowired
    TareaRepository tareaRepository;
    @Override
    public List<ListadoTareasResponse> listar() {
        return tareaRepository.findAll().stream().map(tarea -> {
            ListadoTareasResponse listadoTareasResponse = new ListadoTareasResponse();
            listadoTareasResponse.setId(tarea.getId());
            listadoTareasResponse.setNombre(tarea.getNombre());
            listadoTareasResponse.setNombre_usuario(tarea.getUsuario().getNombres() + " "  + tarea.getUsuario().getApellido_paterno());
            listadoTareasResponse.setEstado_tarea(tarea.getEstadoTarea().getNombre());

            return listadoTareasResponse;

            }).collect(Collectors.toList());
        }

    @Override
    public void crearTarea(Tarea tarea) {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setNombre(tarea.getNombre());
        nuevaTarea.setUsuario(tarea.getUsuario());
        nuevaTarea.setEstadoTarea(tarea.getEstadoTarea());

        tareaRepository.save(nuevaTarea);
    }

    @Override
    public void actualizarTarea(Tarea tarea) {
        Optional<Tarea> tareaOptional  = tareaRepository.findById(tarea.getId());

        if(tareaOptional.isPresent()) {
            Tarea tareaActualizada = tareaOptional.get();

            tareaActualizada.setNombre(tarea.getNombre());
            tareaActualizada.setUsuario(tarea.getUsuario());
            tareaActualizada.setEstadoTarea(tarea.getEstadoTarea());

            tareaRepository.save(tareaActualizada);
        }
    }

    @Override
    public void eliminarTarea(Integer idTarea) {
        tareaRepository.deleteById(idTarea);
    }
}
