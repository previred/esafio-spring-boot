package com.nuevoapp.prueba.domain.service.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.nuevoapp.prueba.domain.model.dto.TasksDto;
import com.nuevoapp.prueba.domain.model.entity.TasksEntity;
import com.nuevoapp.prueba.domain.model.mappers.TasksMapper;
import com.nuevoapp.prueba.domain.repository.TasksRepository;
import com.nuevoapp.prueba.domain.service.TasksService;
import com.nuevoapp.prueba.utils.PatchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository repository;
    private final TasksMapper mapper;
    private final PatchUtils patchUtils;

    @Override
    public TasksDto getTaskById(Integer id){
        Optional<TasksEntity> optTask = repository.findById(id);
        if(optTask.isEmpty()){
            throw new NoSuchElementException("no task found for the given id");
        }
        return mapper.toDto(optTask.get());
    }

    @Override
    public List<TasksDto> getTaskByEmail(String email){
        List<TasksEntity> optTaskList = repository.findAllByUserEmail(email);
        if(optTaskList.isEmpty()){
            throw new NoSuchElementException("no tasks found for the given Email");
        }
        return optTaskList.stream().map(mapper::toDto).toList();
    }

    @Override
    public TasksDto createTask(TasksDto dto){
        //if id is not null and exist in db, reject the update and return DIVE
        if(null != dto.getId()){
            Optional<TasksEntity> optTask = repository.findById(dto.getId());
            if(optTask.isPresent()){
                throw new DataIntegrityViolationException("Task already exists");
            }
        }
        //Update the entity, ignore the id if present
        TasksEntity entity = new TasksEntity();
        entity = mapper.updateTaskFromDto(dto,entity);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<TasksDto> createTaskBatch(List<TasksDto> list){
        //filters the ids if present, if so, search the DB, if at least one exists, throw DIVE
        Set<Integer> idSet = list.stream().map(TasksDto::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        if (!idSet.isEmpty()){
            List<TasksEntity> entities = repository.findAllById(idSet);
            if(!entities.isEmpty()){
                throw new DataIntegrityViolationException("A Task already exists");
            }
        }

        List<TasksEntity> entities = new ArrayList<>(list.size());
        for(TasksDto dto: list){
            TasksEntity entity = new TasksEntity();
            //ignore the ids if present
            entities.add(mapper.updateTaskFromDto(dto,entity));
        }
        return repository.saveAll(entities).stream().map(mapper::toDto).toList();

    }

    @Override
    public TasksDto updateTaskById(TasksDto dto){
        if(null == dto.getId()){
            throw new IllegalArgumentException("id must not be null");
        }

        Optional<TasksEntity> optTask = repository.findById(dto.getId());
        if(optTask.isEmpty()){
            throw new NoSuchElementException("Task does not exist");
        }
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    //method uses the json-patch library + custom implementation to find and update all nodes
    @Override
    public TasksDto patchTaskById(Integer id, JsonPatch patchOperations){
        Optional<TasksEntity> optTask = repository.findById(id);
        if(optTask.isEmpty()){
            throw new NoSuchElementException("Task does not exist");
        }
        TasksDto dto = mapper.toDto(optTask.get());
        dto = patchUtils.applyPatch(dto, patchOperations);
        TasksEntity patchedEntity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(patchedEntity));
    }

    @Override
    public Boolean deleteTaskById(Integer id){
        Optional<TasksEntity> optTask = repository.findById(id);
        if(optTask.isEmpty()){
            throw new NoSuchElementException("Task does not exist");
        }
        repository.deleteById(id);
        Optional<TasksEntity> optTaskDelete = repository.findById(id);
        //if deleted isEmpty = true
        return optTaskDelete.isEmpty();
    }
}
