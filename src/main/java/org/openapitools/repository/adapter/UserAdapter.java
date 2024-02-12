package org.openapitools.repository.adapter;

import org.openapitools.dto.User;
import org.openapitools.dto.UserPaginated;
import org.openapitools.repository.UserRepository;
import org.openapitools.repository.jpa.LoginEntityRepository;
import org.openapitools.repository.jpa.UserEntityRepository;
import org.openapitools.util.MapperUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserAdapter implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final LoginEntityRepository loginEntityRepository;

    public UserAdapter(UserEntityRepository userEntityRepository, LoginEntityRepository loginEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.loginEntityRepository = loginEntityRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        var loginEntity = loginEntityRepository.save(MapperUtil.dtoToLoginEntity(user));
        var userEntity = MapperUtil.dtoToUserEntity(user);
        userEntity.setLogin(loginEntity);
        return MapperUtil.entityToUserDto(userEntityRepository.save(userEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userEntityRepository.findAll()
                .stream()
                .map(MapperUtil::entityToUserDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserPaginated getAllPaginated(Integer size, Integer page) {
        var pageRequest = PageRequest.of(page, size);
        var response = userEntityRepository.findAll(pageRequest);
        var users = response.getContent()
                .stream()
                .map(MapperUtil::entityToUserDto)
                .toList();

        return UserPaginated.builder()
                .users(users)
                .pagination(MapperUtil.pageToDto(response.getPageable(), (int) response.getTotalElements()))
                .build();

    }
}
