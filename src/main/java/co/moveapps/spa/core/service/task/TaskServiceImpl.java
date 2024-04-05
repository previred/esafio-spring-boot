package co.moveapps.spa.core.service.task;

import co.moveapps.spa.core.constants.MessageResponseConstant;
import co.moveapps.spa.core.controller.model.StatusResponse;
import co.moveapps.spa.core.controller.model.TaskRequest;
import co.moveapps.spa.core.controller.model.TaskResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.model.entity.TaskEntity;
import co.moveapps.spa.core.model.repository.IStatusTaskEntityRepository;
import co.moveapps.spa.core.model.repository.ITaskEntityRepository;
import co.moveapps.spa.core.service.IGenericCRUDService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements IGenericCRUDService<TaskRequest, TaskResponse, UUID> {

    private final ITaskEntityRepository taskEntityRepository;
    private final IStatusTaskEntityRepository statusTaskEntityRepository;

    public TaskServiceImpl(ITaskEntityRepository taskEntityRepository, IStatusTaskEntityRepository statusTaskEntityRepository) {
        this.taskEntityRepository = taskEntityRepository;
        this.statusTaskEntityRepository = statusTaskEntityRepository;
    }

    @Override
    public TaskResponse create(TaskRequest object) {
        return null;
    }

    @Override
    public TaskResponse update(TaskRequest object) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) throws BusinessException {
        TaskEntity user = this.taskEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_NOT_DATA_FOUND));
        taskEntityRepository.deleteById(user.getId());
        return Boolean.TRUE;
    }

    @Override
    public TaskResponse getById(UUID id) throws BusinessException {
        TaskEntity entity = this.taskEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_NOT_DATA_FOUND));
        return new TaskResponse(entity);
    }

    @Override
    public List<TaskResponse> getAll(Integer page, Integer size) {
        return this.taskEntityRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }

    public List<StatusResponse> getAllStates(Integer page, Integer limit) {
        return this.statusTaskEntityRepository.findAll(PageRequest.of(page, limit))
                .stream()
                .map(StatusResponse::new)
                .collect(Collectors.toList());
    }
}
