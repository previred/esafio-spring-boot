package cl.previred.desafio.service.impl;

import cl.previred.desafio.entity.StateTaskEntity;
import cl.previred.desafio.enums.StateTaskEnum;
import cl.previred.desafio.repository.StateTaskRepository;
import cl.previred.desafio.service.StateTaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StateTaskServiceImpl implements StateTaskService {

    private final StateTaskRepository stateTaskRepository;

    @Override
    public StateTaskEntity createStateTask(StateTaskEnum stateTask) {
        return stateTaskRepository.save(StateTaskEntity.builder()
                .name(stateTask.name()).build());
    }

    @Override
    public StateTaskEntity findByName(StateTaskEnum stateTask) {
        return stateTaskRepository.findByName(stateTask.name())
                .orElseThrow(() -> new EntityNotFoundException(String.format("status %s not found", stateTask.name())));
    }
}
