package com.previred.desafioGestionTareas.services.Impl;

import com.previred.desafioGestionTareas.configs.ModelMapperConfig;
import com.previred.desafioGestionTareas.dtos.TareaDTO;
import com.previred.desafioGestionTareas.entities.Tarea;
import com.previred.desafioGestionTareas.persistence.TareaDAO;
import com.previred.desafioGestionTareas.services.TareaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaServicesImpl implements TareaServices {

    @Autowired
    private TareaDAO tareaDAO;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Override
    public List<TareaDTO> findAll() {
        List<Tarea> tasks = tareaDAO.findAll();
        return tasks.stream().map(task -> modelMapper.modelMapper().map(task, TareaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<TareaDTO> findById(Long id) {
        Optional<Tarea> taskOptional = tareaDAO.findById(id);

        if(taskOptional.isPresent()){
            return taskOptional.map(task -> modelMapper.modelMapper().map(task, TareaDTO.class));
        }
        //validar si existe o mandar exception
        return null;
    }

    @Override
    public void save(TareaDTO tareaDTO) {

        Tarea tarea = modelMapper.modelMapper().map(tareaDTO, Tarea.class);
        tareaDAO.save(tarea);
    }

    @Override
    public Optional<TareaDTO> update(Long id, TareaDTO tareaDTO) {

        return tareaDAO.findById(id).map(tareaExistente -> {

            tareaDTO.setId(id);
            tareaExistente.setDescription(tareaDTO.getDescription());
            tareaExistente.setState(tareaDTO.getState());

            // Guardar los cambios en la base de datos
            Tarea tarea = modelMapper.modelMapper().map(tareaDTO, Tarea.class);

            System.out.println("tarea : id : "+tarea.getId() + " tarea desripcion : "+ tarea.getDescription() + " tarea stado : "+ tarea.getState());
            tareaDAO.save(tarea);

            TareaDTO taskDTOUpdated = modelMapper.modelMapper().map(tarea, TareaDTO.class);
            // Convertir la entidad actualizada a un DTO y devolverlo
            return taskDTOUpdated;
        });
    }

    @Override
    public void deleteById(Long id) {

        if (tareaDAO.findById(id).isPresent()) {
            tareaDAO.deleteById(id);
        }

    }
}