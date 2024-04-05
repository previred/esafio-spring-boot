package co.moveapps.spa.core.service.user;

import co.moveapps.spa.core.constants.MessageResponseConstant;
import co.moveapps.spa.core.controller.model.UserRequest;
import co.moveapps.spa.core.controller.model.UserResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.model.entity.UserEntity;
import co.moveapps.spa.core.model.repository.IUserEntityRepository;
import co.moveapps.spa.core.service.IGenericCRUDService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IGenericCRUDService<UserRequest, UserResponse, UUID> {

    private final IUserEntityRepository userEntityRepository;

    public UserServiceImpl(IUserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserResponse create(UserRequest object) {
        return null;
    }

    @Override
    public UserResponse update(UserRequest object) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) throws BusinessException {
        UserEntity user = this.userEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_NOT_DATA_FOUND));
        userEntityRepository.deleteById(user.getId());
        return Boolean.TRUE;
    }

    @Override
    public UserResponse getById(UUID id) throws BusinessException {
        UserEntity entity = this.userEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_NOT_DATA_FOUND));
        return new UserResponse(entity);
    }

    @Override
    public List<UserResponse> getAll(Integer page, Integer size) {
        return this.userEntityRepository.findAll(PageRequest.of(0, size))
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

}
