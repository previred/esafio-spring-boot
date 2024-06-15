package com.enterprise.move.apps.MoveApps.service;

import com.enterprise.move.apps.MoveApps.exception.TaskValidationException;
import com.enterprise.move.apps.MoveApps.model.StatusTask;
import com.enterprise.move.apps.MoveApps.model.TaskModel;
import com.enterprise.move.apps.MoveApps.model.User;
import com.enterprise.move.apps.MoveApps.process.TaskProcessor;
import com.enterprise.move.apps.MoveApps.repository.StatusTaskRepository;
import com.enterprise.move.apps.MoveApps.repository.TaskRepository;
import com.enterprise.move.apps.MoveApps.repository.UserRepository;
import org.openapitools.model.TaskSuccessful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.openapitools.model.Task;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final TaskProcessor taskProcessor;

    private final UserRepository userRepository;

    private final StatusTaskRepository statusTaskRepository;

    public TaskService(TaskRepository taskRepository, TaskProcessor taskProcessor, UserRepository userRepository, StatusTaskRepository statusTaskRepository) {
        this.taskRepository = taskRepository;
        this.taskProcessor = taskProcessor;
        this.userRepository = userRepository;
        this.statusTaskRepository = statusTaskRepository;
    }

    public List<Task> getTask(){
        List<TaskModel> taskModelList = Optional.ofNullable(taskRepository.findAll())
                .orElse(Collections.emptyList());

        return taskProcessor.buildTask(taskModelList);
    }

    @Transactional
    public TaskSuccessful createTask(Task task) {
        try {
            User user = userRepository.findByName(task.getResponsible());
            validateUserTask(user);
            StatusTask statusTask = statusTaskRepository.findByNameStatusTask(task.getStatusTask());

            validateStatusTask(statusTask);

            TaskModel taskModel = taskProcessor.convertTaskToTaskModel(task, user, statusTask);

            taskRepository.save(taskModel);
            return new TaskSuccessful("0", "Create Successful");
        } catch (TaskValidationException e) {
            return new TaskSuccessful(e.getCode(), e.getMessage());
        }
    }


    private void validateUserTask(User user) throws TaskValidationException {
        if (user == null) {
            throw new TaskValidationException("-1", "Name is invalid");
        }
    }

    private void validateStatusTask(StatusTask statusTask) throws TaskValidationException {
        if (statusTask == null) {
            throw new TaskValidationException("-2", "Status Task is invalid");
        }
    }


    @Transactional
    public TaskSuccessful updateTask(Integer id,Task task) {
        try {

           Optional<TaskModel> taskModelRepository = taskRepository.findById(Long.valueOf(id));
           validateId(taskModelRepository);

            User user = userRepository.findByName(task.getResponsible());
            validateUserTask(user);
            StatusTask statusTask = statusTaskRepository.findByNameStatusTask(task.getStatusTask());

            validateStatusTask(statusTask);
            task.setIdTask(String.valueOf(id));
            TaskModel taskModel = taskProcessor.convertTaskToTaskModelUpdate(task, user, statusTask);

            taskRepository.save(taskModel);
            return new TaskSuccessful("0", "Update Successful");
        } catch (TaskValidationException e) {
            return new TaskSuccessful(e.getCode(), e.getMessage());
        }
    }

    private void validateId(Optional<TaskModel> taskModel) {
        if(taskModel.isEmpty()){
            throw new TaskValidationException("-3", "ID not exist");
        }
    }



    public void deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id.longValue());
        }catch (Exception e){
            logger.info("No Exist but not exist error");
        }

    }


}
