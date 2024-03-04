package cl.previred.desafio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.previred.desafio.dto.TareasDto;
import cl.previred.desafio.mapper.TareasMapper;
import cl.previred.desafio.model.EstadosTarea;
import cl.previred.desafio.model.Tareas;
import cl.previred.desafio.repository.EstadosTareaRepository;
import cl.previred.desafio.repository.TareasRepository;
import cl.previred.desafio.service.TareasService;


@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository taskRepository;

    @Autowired
    private EstadosTareaRepository statusTaskRepository;

    @Autowired
    private TareasMapper taskMapper;

    @Transactional
    public String saveTask(List<Tareas> tasks) throws Exception{
        String message = "";
        for(Tareas t : tasks) {
            EstadosTarea statusTask = statusTaskRepository.findStatusByName(t.getStatusTask().getStatus());
            if(statusTask != null) {
                t.setStatusTask(statusTask);
                taskRepository.save(t);
                message = message + "- Se guardo tarea " + t.getNameTask() + " correctamente";
            }
            else {
                message = message + "- No se encontro status " + t.getStatusTask().getStatus() + " de la tarea " + t.getNameTask();
            }
        }

        return message;
    }

    public String updateTask(Tareas task) throws Exception{
        String message = "";
        EstadosTarea statusTask = statusTaskRepository.findStatusByName(task.getStatusTask().getStatus());
        if(statusTask != null) {
            task.setStatusTask(statusTask);
            taskRepository.save(task);
            message = "- Se actualizo tarea " + task.getNameTask() + " correctamente";
        }
        else {
            message = "No se encontro status " + task.getStatusTask().getStatus() + " de la tarea " + task.getNameTask();
        }
        return message;
    }

    public List<TareasDto> getAllTasks() throws Exception {
        return taskRepository.findAll().stream().map(taskMapper::taskToDto).collect(Collectors.toList());
    }

    public TareasDto findTaskById(int id) throws Exception {
        return taskMapper.taskToDto(taskRepository.findById(id).get());
    }

    public void deleteTaskById(int id) throws Exception {
        taskRepository.deleteById(id);
    }

}
