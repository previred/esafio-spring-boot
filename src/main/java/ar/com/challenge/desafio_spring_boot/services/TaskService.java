package ar.com.challenge.desafio_spring_boot.services;

import ar.com.challenge.desafio_spring_boot.dto.TaskDto;
import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAll();
    TaskDto save(TaskDto task) throws ResourceFoundException, ResourceNotFoundException;
    TaskDto getById(Integer id) throws ResourceNotFoundException;
    TaskDto delete(Integer id) throws ResourceNotFoundException;
    TaskDto update(TaskDto task) throws ResourceNotFoundException;
}
