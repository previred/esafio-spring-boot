package com.test.moveapps.service;

import com.test.moveapps.domain.dto.AssignTaskDto;
import com.test.moveapps.domain.dto.AssignTaskUpdateDto;
import com.test.moveapps.domain.entity.AssignTask;
import com.test.moveapps.domain.entity.Task;
import com.test.moveapps.domain.entity.TaskStatus;
import com.test.moveapps.domain.entity.User;
import com.test.moveapps.exception.UserApiActionException;
import com.test.moveapps.repository.AssignTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AssignTaskServiceImpl implements AssignTaskService{

    @Autowired
    private AssignTaskRepository assignTaskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskStatusService taskStatusService;

    @Override
    public Boolean assignTaskUser(AssignTaskDto assignTaskDto) {

        AssignTask assignTask = new AssignTask();
        assignTask.setId_user_assign(assignTaskDto.getIdUserAssignTask());
        assignTask.setId_task_status(assignTaskDto.getIdTaskStatus());
        assignTask.setId_task(assignTaskDto.getIdTask());

        this.assignTaskRepository.save(assignTask);
        return true;
    }

    @Override
    public List<AssignTaskDto> assignTaskList() {

        List<AssignTaskDto> assignTaskDto = new ArrayList<>();
        List<AssignTask> listOptional =  this.assignTaskRepository.findAll();

        if(!listOptional.isEmpty()){

            listOptional.forEach(assignTask -> {
                AssignTaskDto assign = new AssignTaskDto();
                assign.setIdAssignTask(assignTask.getId());

                assign.setIdUserAssignTask(assignTask.getId_user_assign());
                Optional<User> user = userService.findById(assignTask.getId_user_assign());
                user.ifPresent( usr -> assign.setUserAssignTask(usr.getUsername()));

                assign.setIdTaskStatus(assignTask.getId_task_status());
                Optional<TaskStatus> taskStatus = taskStatusService.findById(assignTask.getId_task_status());
                taskStatus.ifPresent(tsk -> assign.setTaskStatus(tsk.getTask_status_name()));

                assign.setIdTask(assignTask.getId_task());
                Optional<Task> task = taskService.findTaskById(assignTask.getId_task());
                task.ifPresent(tsk -> assign.setTaskName(tsk.getTask_name()));

                assignTaskDto.add(assign);

            });

        }

        return assignTaskDto;
    }

    @Override
    public Boolean assignTaskUpdate(AssignTaskUpdateDto assignTaskUpdateDto) {

        Optional<AssignTask> assignTask = assignTaskRepository.findAssignTaskById(assignTaskUpdateDto.getIdAssignTask());

        if(assignTask.isPresent()){

            if(assignTask.get().getId_task_status()==assignTaskUpdateDto.getIdTaskStatus()){
                throw new UserApiActionException("No se puede cambiar el estado de la tarea al estado actual", HttpStatus.CONFLICT);
            }

        }

        assignTask.ifPresentOrElse(task -> task.setId_task_status(assignTaskUpdateDto.getIdTaskStatus()),
                () -> {throw new UserApiActionException("No se pudo encontrar la tarea asignada", HttpStatus.CONFLICT);});
        assignTaskRepository.save(assignTask.get());

        return true;
    }


}
