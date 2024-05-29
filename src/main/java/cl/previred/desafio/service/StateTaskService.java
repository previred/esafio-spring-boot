package cl.previred.desafio.service;

import cl.previred.desafio.entity.StateTaskEntity;
import cl.previred.desafio.enums.StateTaskEnum;
import org.springframework.stereotype.Service;

@Service
public interface StateTaskService {
    StateTaskEntity createStateTask(StateTaskEnum stateTask);

    StateTaskEntity findByName(StateTaskEnum stateTask);
}
