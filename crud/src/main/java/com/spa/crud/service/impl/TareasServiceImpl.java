package com.spa.crud.service.impl;

import com.spa.crud.dto.EstadosTareaDTO;
import com.spa.crud.dto.TareasDTO;
import com.spa.crud.exception.CrudException;
import com.spa.crud.mapper.TareasMapper;
import com.spa.crud.model.EstadosTarea;
import com.spa.crud.model.Tareas;
import com.spa.crud.repository.EstadosTareaRepository;
import com.spa.crud.repository.TareasRepository;
import com.spa.crud.service.TareasService;
import com.spa.crud.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareasServiceImpl implements TareasService {
    private static final Logger logger = LoggerFactory.getLogger(TareasServiceImpl.class);

    @Autowired
    private TareasRepository tareasRepository;

    @Autowired
    private EstadosTareaRepository estadosTareaRepository;

    @Autowired
    private TareasMapper taskMapper;

    @Transactional
    public String saveTask(Tareas tareas) {
        String message = "";
        EstadosTarea statusTask = estadosTareaRepository.findStatusByName(tareas.getEstadosTarea().getNombreEstado());
        if(statusTask != null) {
            int numeroTarea = tareasRepository.getLastNumberTask();
            tareas.setNumeroTarea(String.valueOf(numeroTarea + 1));
            logger.info("AERS: " + tareasRepository.getLastNumberTask());
            tareas.setEstadosTarea(statusTask);
            tareasRepository.save(tareas);
            message = message + "- Se guardo tarea " + tareas.getNombreTarea() + " correctamente";
        }
        else {
            message = message + "- No se encontro status " + tareas.getEstadosTarea().getNombreEstado() + " de la tarea " + tareas.getNombreTarea();
        }

        return message;
    }

    @Override
    public String updateTask(Tareas task) {
        String message = "";
        EstadosTarea statusTask = estadosTareaRepository.findStatusByName(task.getEstadosTarea().getNombreEstado());
        if(statusTask != null) {
            task.setEstadosTarea(statusTask);
            tareasRepository.save(task);
            message = "- Se actualizo tarea " + task.getNombreTarea() + " correctamente";
        }
        else {
            message = "No se encontro status " + task.getEstadosTarea().getNombreEstado() + " de la tarea " + task.getNombreTarea();
        }
        return message;
    }

    @Override
    public List<TareasDTO> getAllTasks()  {
        return tareasRepository.getAll().stream().map(taskMapper::taskToDto).toList();
    }

    public TareasDTO findTaskById(Long id) {
        return convertToDTO(tareasRepository.getAllById(id));
    }

    public TareasDTO convertToDTO(Tareas tareas){
        TareasDTO dto = new TareasDTO();
            dto.setId(tareas.getId());
            dto.setNombreTarea(tareas.getNombreTarea());
            dto.setNumeroTarea(tareas.getNumeroTarea());
            EstadosTareaDTO estadosTareaDTO = new EstadosTareaDTO(tareas.getEstadosTarea().getIdEstado(), tareas.getEstadosTarea().getNombreEstado());
            dto.setEstadosTarea(estadosTareaDTO);
        return dto;
    }

    @Transactional
    public void deleteTaskByNumeroTarea(String numeroTarea)  {
//        checkTarea(numeroTarea);
        Long id = tareasRepository.getIdByNumeroTarea(numeroTarea);
        logger.info("AERS: " + id);
        tareasRepository.deleteById(id);
    }

    private void checkTarea(Long numeroTarea){
        if (tareasRepository.findByNumeroTarea(numeroTarea).isEmpty()){
            logger.error(Constants.USER_NOT_FOUND);
            throw new CrudException(Constants.USER_NOT_FOUND);
        }
    }
}
