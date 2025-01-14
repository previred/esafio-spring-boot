package com.previred.gestion_tareas_api.services.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.gestion_tareas_api.configs.ModelMapperConfig;
import com.previred.gestion_tareas_api.dtos.TaskDTO;
import com.previred.gestion_tareas_api.entities.Task;
import com.previred.gestion_tareas_api.persistence.TaskDAO;
import com.previred.gestion_tareas_api.services.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Override
    public List<TaskDTO> findAll() {

         List<Task> tasks = taskDAO.findAll();
         
         return tasks.stream().map(task -> modelMapper.modelMapper().map(task, TaskDTO.class)).collect(Collectors.toList());
                  
    
    }

    @Override
    public Optional<TaskDTO> findById(Long id) {

        Optional<Task> taskOptional = taskDAO.findById(id);

        if(taskOptional.isPresent()){

            return taskOptional.map(task -> modelMapper.modelMapper().map(task, TaskDTO.class));
        }
        //validar si existe o mandar exception
        return null;
        
        
    }

    @Override
    public void save(TaskDTO taskDTO) {

        Task task = modelMapper.modelMapper().map(taskDTO, Task.class);
 
        taskDAO.save(task);
    }

    @Override
    public Optional<TaskDTO> update(Long id, TaskDTO taskDTO) {


        return taskDAO.findById(id).map(existingTask -> {

            taskDTO.setId(id);
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setState(taskDTO.getState());

            
            // Guardar los cambios en la base de datos
            Task task = modelMapper.modelMapper().map(taskDTO, Task.class);
            
            System.out.println("task : id : "+task.getId() + " tasks description : "+ task.getDescription() + " task state : "+ task.getState());
            taskDAO.save(task);
            
            TaskDTO taskDTOUpdated = modelMapper.modelMapper().map(task, TaskDTO.class);
            // Convertir la entidad actualizada a un DTO y devolverlo
            return taskDTOUpdated;
        });

    }

    

    @Override
    public void deleteById(Long id) {
        if (taskDAO.findById(id).isPresent()) {
            taskDAO.deleteById(id);
            }

            //devolver error
        }
        

   
}
