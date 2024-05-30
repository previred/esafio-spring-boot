package com.nuevospa.taskmanager.repository.adapter;

import com.nuevospa.taskmanager.dto.User;
import com.nuevospa.taskmanager.repository.UserRepository;
import com.nuevospa.taskmanager.repository.jpa.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nuevospa.taskmanager.util.MapperUtil;


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

}
