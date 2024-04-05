package co.moveapps.spa.core.service.task;

import co.moveapps.spa.core.constants.MessageResponseConstant;
import co.moveapps.spa.core.controller.model.StatusResponse;
import co.moveapps.spa.core.controller.model.TaskRequest;
import co.moveapps.spa.core.controller.model.TaskResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.model.entity.StatusTaskEntity;
import co.moveapps.spa.core.model.entity.TaskEntity;
import co.moveapps.spa.core.model.entity.UserEntity;
import co.moveapps.spa.core.model.repository.IStatusTaskEntityRepository;
import co.moveapps.spa.core.model.repository.ITaskEntityRepository;
import co.moveapps.spa.core.model.repository.IUserEntityRepository;
import co.moveapps.spa.core.service.IGenericCRUDService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements IGenericCRUDService<TaskRequest, TaskResponse, UUID> {

    private final ITaskEntityRepository taskEntityRepository;
    private final IStatusTaskEntityRepository statusTaskEntityRepository;
    private final IUserEntityRepository userEntityRepository;

    public TaskServiceImpl(ITaskEntityRepository taskEntityRepository, IStatusTaskEntityRepository statusTaskEntityRepository,
                           IUserEntityRepository userEntityRepository) {
        this.taskEntityRepository = taskEntityRepository;
        this.statusTaskEntityRepository = statusTaskEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public TaskResponse create(TaskRequest request) throws BusinessException {
        UserEntity user = null;
        if (Optional.ofNullable(request).map(TaskRequest::getUser).isPresent())
            user = userEntityRepository.findById(request.getUser())
                    .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_USER_NOT_FOUND));

        StatusTaskEntity status = null;
        if (Optional.ofNullable(request).map(TaskRequest::getStatus).map(StatusResponse::getId).isPresent())
            status = statusTaskEntityRepository.findById(request.getStatus().getId().intValue())
                    .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_STATUS_NOT_FOUND));

        TaskEntity task = taskEntityRepository.save(
                TaskEntity.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .user(user)
                        .state(status).build()
        );

        return new TaskResponse(task);
    }

    @Override
    public TaskResponse update(UUID id, TaskRequest object) throws BusinessException {
        TaskEntity entity = taskEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_TASK_NOT_FOUND));

        if (Optional.ofNullable(object).map(TaskRequest::getUser).isPresent()) {
            UserEntity user = userEntityRepository.findById(object.getUser())
                    .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_USER_NOT_FOUND));
            entity.setUser(user);
        }

        if (Optional.ofNullable(object).map(TaskRequest::getStatus).map(StatusResponse::getId).isPresent()) {
            StatusTaskEntity status = statusTaskEntityRepository.findById(object.getStatus().getId().intValue())
                    .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_STATUS_NOT_FOUND));
            entity.setState(status);
        }

        if (Optional.ofNullable(object).map(TaskRequest::getDescription).isPresent())
            entity.setDescription(object.getDescription());

        if (Optional.ofNullable(object).map(TaskRequest::getTitle).isPresent())
            entity.setDescription(object.getTitle());

        TaskEntity task = taskEntityRepository.save(entity);

        return new TaskResponse(task);
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
