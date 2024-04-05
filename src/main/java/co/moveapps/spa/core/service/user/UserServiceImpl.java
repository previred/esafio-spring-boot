package co.moveapps.spa.core.service.user;

import co.moveapps.spa.core.constants.MessageResponseConstant;
import co.moveapps.spa.core.controller.model.CredentialsRequest;
import co.moveapps.spa.core.controller.model.UserRequest;
import co.moveapps.spa.core.controller.model.UserResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.model.entity.AuthUserEntity;
import co.moveapps.spa.core.model.entity.UserEntity;
import co.moveapps.spa.core.model.repository.IUserEntityRepository;
import co.moveapps.spa.core.service.IGenericCRUDService;
import co.moveapps.spa.core.utility.SecurityCommonUtility;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IGenericCRUDService<UserRequest, UserResponse, UUID> {

    private final IUserEntityRepository userEntityRepository;

    public UserServiceImpl(IUserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserResponse create(UserRequest request) throws BusinessException {
        final String email = request.getCredentials().getEmail();
        if (userEntityRepository.existsAllByAuthenticationUsername(email))
            throw new BusinessException(MessageResponseConstant.EXCEPTION_USERNAME_ALREADY_REGISTER);

        AuthUserEntity authUserEntity = AuthUserEntity.builder()
                .username(email)
                .password(SecurityCommonUtility.getInstance().encode(request.getCredentials().getPassword()))
                .build();

        UserEntity userEntity = UserEntity.builder()
                .authentication(authUserEntity)
                .phone(new BigInteger(request.getPhone()))
                .enable(request.getEnable())
                .firstname(request.getFirstName())
                .lastname(request.getLastName()).build();

        authUserEntity.setUser(userEntity);

        userEntityRepository.save(userEntity);

        return new UserResponse(userEntity);
    }

    @Override
    public UserResponse update(UUID id, UserRequest object) throws BusinessException {
        UserEntity entity = userEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_USER_NOT_FOUND));

        if (Optional.ofNullable(object).map(UserRequest::getEnable).isPresent())
            entity.setEnable(object.getEnable());

        if (Optional.ofNullable(object).map(UserRequest::getFirstName).isPresent())
            entity.setFirstname(object.getFirstName());

        if (Optional.ofNullable(object).map(UserRequest::getLastName).isPresent())
            entity.setLastname(object.getLastName());

        if (Optional.ofNullable(object).map(UserRequest::getPhone).isPresent())
            entity.setPhone(new BigInteger(object.getPhone()));

        if (Optional.ofNullable(object).map(UserRequest::getCredentials).map(CredentialsRequest::getEmail).isPresent()) {
            AuthUserEntity authUser = entity.getAuthentication();
            authUser.setUsername(authUser.getUsername());
            entity.setAuthentication(authUser);
        }

        if (Optional.ofNullable(object).map(UserRequest::getCredentials).map(CredentialsRequest::getPassword).isPresent()) {
            AuthUserEntity authUser = entity.getAuthentication();
            authUser.setPassword(SecurityCommonUtility.getInstance().encode(object.getCredentials().getPassword()));
            entity.setAuthentication(authUser);
        }

        UserEntity user = userEntityRepository.save(entity);

        return new UserResponse(user);

    }

    @Override
    public List<UserResponse> getAll(Integer page, Integer size) {
        return this.userEntityRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getById(UUID id) throws BusinessException {
        UserEntity entity = this.userEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_NOT_DATA_FOUND));
        return new UserResponse(entity);
    }

    @Override
    public Boolean delete(UUID id) throws BusinessException {
        UserEntity user = this.userEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_NOT_DATA_FOUND));
        userEntityRepository.deleteById(user.getId());
        return Boolean.TRUE;
    }


}
