package com.springboot.desafio.services.impl;

import com.springboot.desafio.converters.TareaRqDTOConverter;
import com.springboot.desafio.exceptions.TareaException;
import com.springboot.desafio.model.*;
import com.springboot.desafio.repository.EstadoTareaRepository;
import com.springboot.desafio.repository.TareaRepository;
import com.springboot.desafio.services.TareaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.springboot.desafio.constants.Constantes.ESTADO_TAREA_PENDIENTE;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Autowired
    private TareaRqDTOConverter tareaRqDTOConverter;

    private final ModelMapper modelMapper;

    public TareaServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TareaRsDTO crearTarea(TareaRqDTO tareaRqDTO){
        try{
            Tarea tarea = tareaRqDTOConverter.convert(tareaRqDTO);

            if( tarea == null ){
                throw new TareaException("Error al convertir TareaRqDTO a Tarea.");
            }

            EstadoTarea estadoTarea = estadoTareaRepository.getEstadoTareaByNombre(ESTADO_TAREA_PENDIENTE);
            tarea.setEstadoTareaId(estadoTarea.getId());

            tareaRepository.save(tarea);
            return modelMapper.map(tarea, TareaRsDTO.class);
        }catch (Exception e){
            throw new TareaException("Error al crear la tarea: " + e.getMessage());
        }
    }

    @Override
    public void eliminarTarea(Long id){
        try {
            if( !tareaRepository.existsById(id)){
                throw new TareaException( "Tarea no existe."  );
            }
            Tarea tarea = tareaRepository.getReferenceById(id);
            tareaRepository.delete(tarea);
        }catch(Exception e){
            throw new TareaException("No se pudo eliminar la tarea ID: " + id + "." + e.getMessage());
        }
    }

    @Override
    public TareaRsDTO getTareaById(Long id){
        try {
            if( !tareaRepository.existsById(id) ){
                throw new TareaException("Tarea no existe.");
            }
            Tarea tarea = tareaRepository.getReferenceById(id);
            return modelMapper.map(tarea, TareaRsDTO.class);
        }catch (Exception e){
            throw new TareaException("No se pudo obtener la tarea. " + e.getMessage());
        }
    }

    @Override
    public List<TareaRsDTO> findAll(){
        try {
            List<Tarea> tareaList = tareaRepository.findAll();
            return tareaList.stream()
                    .map(tarea -> modelMapper.map(tarea, TareaRsDTO.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new TareaException("No se pudo obtener la lista de tareas. " + e.getMessage());
        }
    }

    @Override
    public TareaRsDTO updateTareaById(Long id, TareaEstRqDTO tareaRqDTO){
        try{

            if( !tareaRepository.existsById(id) ){
                throw new TareaException("Tarea no existe.");
            }

            Tarea tarea = tareaRepository.getReferenceById(id);
            tarea.setUsuarioId(tareaRqDTO.getUsuarioId());
            tarea.setNombre(tareaRqDTO.getNombre());
            tarea.setStoryPoint(tareaRqDTO.getStoryPoint());
            tarea.setDescripcion(tareaRqDTO.getDescripcion());
            tarea.setEstadoTareaId(tareaRqDTO.getEstadoTareaId());

            tareaRepository.save(tarea);
            return modelMapper.map(tarea, TareaRsDTO.class);
        }catch(Exception e){
            throw new TareaException("No se pudo editar Tarea con ID: " + id + "." + e.getMessage());
        }
    }
}
