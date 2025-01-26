package cl.previred.task.taskpreviredback.service.impl;

import cl.previred.task.taskpreviredback.domain.Task;
import cl.previred.task.taskpreviredback.repository.StateTaskRepository;
import cl.previred.task.taskpreviredback.service.StateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateTaskServiceImpl implements StateTaskService {

    private final StateTaskRepository stateTaskRepository;

    @Autowired
    public StateTaskServiceImpl(StateTaskRepository stateTaskRepository) {
        this.stateTaskRepository = stateTaskRepository;
    }


    @Override
    public List<Task> getStateTask() {
        return List.of();
    }
}
