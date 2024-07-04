package com.example.demo.bussineslogic.serviceImpl;

import com.example.demo.bussineslogic.service.TaskService;
import com.example.demo.model.DTO.DtoTask;
import com.example.demo.model.DTO.DtoTaskResponse;
import com.example.demo.model.entity.Task;
import com.example.demo.model.entity.TaskStatus;
import com.example.demo.model.repository.TaskRepository;
import com.example.demo.model.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository oTaskRepository;
    @Autowired
    private UserDetailsServiceImpl oUserDetailsServiceImpl;
    @Autowired
    private TaskStatusRepository oTaskStatusRepository;

    @Override
    public void save(DtoTask oDtoTask) throws Exception {
        //BUSCA EL CODIGO DEL STATUS HABER SI EXISTE
        TaskStatus oTaskStatus = getTaskStatusByCode(oDtoTask.getCodigoStatus());

        Task oTask = Task.builder()
                .statusCode(oTaskStatus)
                .name(oDtoTask.getName())
                .userEntity(oUserDetailsServiceImpl.getoUserEntity())
                .build();
        oTaskRepository.save(oTask);
//        return new MessageResponse("Creado correctamente.");
    }

    private TaskStatus getTaskStatusByCode(String codigoStatus) throws Exception {
        Optional<TaskStatus> optionalTaskStatus = oTaskStatusRepository.findOneByStatusCode(codigoStatus);
        if (optionalTaskStatus.isPresent()) {
            return optionalTaskStatus.get();
        } else {
            throw new Exception("El codigo del estado de la tarea es invalido.");
        }
    }

    @Override
    public List<DtoTaskResponse> findAll() {
        List<Task> listTask = oTaskRepository.findAll();
        List<DtoTaskResponse> taskResponseList = listTask.stream()
                .map(oTask -> {
                    DtoTaskResponse taskResponse = new DtoTaskResponse();
                    taskResponse.setIdTask(oTask.getId());
                    taskResponse.setNameTask(oTask.getName());
                    taskResponse.setStatusCode(oTask.getStatusCode().getStatusCode());
                    taskResponse.setUsername(oTask.getUserEntity().getUsername());
                    return taskResponse;
                })
                .collect(Collectors.toList());

//        DtoTaskResponse tasksResponse = new DtoTaskResponse();
//        tasksResponse.setTaskResponseList(taskResponseList);
        return taskResponseList;
    }

    @Override
    public DtoTaskResponse getTaskById(Long id) throws Exception {
        return oTaskRepository.findById(id)
                .map(task -> {
                    DtoTaskResponse taskResponse = new DtoTaskResponse();
                    taskResponse.setIdTask(task.getId());
                    taskResponse.setNameTask(task.getName());
                    taskResponse.setStatusCode(task.getStatusCode().getStatusCode());
                    taskResponse.setUsername(task.getUserEntity().getUsername());
                    return taskResponse;
                })
                .orElseThrow(() -> new Exception("Task no encontrada con ID: " + id));

    }

    @Override
    public void update(Long id, DtoTask oDtoTaskDetails) throws Exception {
        Task oTaskExistente = oTaskRepository.findById(id).orElseThrow(() -> new Exception("Task no encontrada con ID: " + id));

        TaskStatus oTaskStatus = getTaskStatusByCode(oDtoTaskDetails.getCodigoStatus());

        oTaskExistente.setName(oDtoTaskDetails.getName());
        oTaskExistente.setStatusCode(oTaskStatus);
        oTaskRepository.save(oTaskExistente);
//        return new MessageResponse("Actualizazdo correctamente.");
    }

    @Override
    public void delete(Long id) {
        Optional<Task> optionalTask = oTaskRepository.findById(id);
        if(optionalTask.isPresent()){
            oTaskRepository.delete(optionalTask.get());
        }else {
            throw new NoSuchElementException("Task no encontrada con ID: " + id);
        }

    }

}
