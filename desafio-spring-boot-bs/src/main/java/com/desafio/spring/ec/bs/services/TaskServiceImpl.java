package com.desafio.spring.ec.bs.services;

import com.desafio.spring.ec.bs.repository.TaskRepository;
import com.desafio.spring.ec.bs.utils.GenericConverterUtils;
import com.desafio.spring.ec.ds.dto.TasksTO;
import com.desafio.spring.ec.ds.entity.Tasks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService{

    private final TaskRepository taskRepository;
    private final GenericConverterUtils genericConverterUtils;


    @Override
    public TasksTO save(TasksTO tasksTO) throws Exception {
        Tasks task =  genericConverterUtils.convertToEntity(tasksTO, Tasks.class);
        taskRepository.saveAndFlush(task);
        tasksTO = genericConverterUtils.convertToDto(task, TasksTO.class);
        return tasksTO;
    }

    @Override
    public TasksTO update(Long aLong, TasksTO tasksTO) throws Exception {
        Tasks task = taskRepository.findById(aLong).orElseThrow(() -> new Exception("ID NOT FOUND: " + aLong)) ;
        task = genericConverterUtils.convertToEntity(tasksTO, Tasks.class);
        taskRepository.saveAndFlush(task);
        tasksTO = genericConverterUtils.convertToDto(task, TasksTO.class);
        return tasksTO;
    }

    @Override
    public List<TasksTO> findAll() throws Exception {
        List<Tasks> tasks = taskRepository.findAll();
        return genericConverterUtils.convertListToListDto(tasks, TasksTO.class);
    }

    @Override
    public TasksTO findById(Long aLong) throws Exception {
        Tasks task = taskRepository.findById(aLong).orElseThrow(() -> new Exception("ID NOT FOUND: " + aLong));
        return genericConverterUtils.convertToDto(task, TasksTO.class);
    }

    @Override
    public void delete(Long aLong) throws Exception {
        taskRepository.deleteById(aLong);
    }
}
