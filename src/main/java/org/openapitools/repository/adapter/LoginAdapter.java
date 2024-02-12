package org.openapitools.repository.adapter;

import org.openapitools.dto.Login;
import org.openapitools.dto.UserLogin;
import org.openapitools.repository.LoginRepository;
import org.openapitools.repository.jpa.LoginEntityRepository;
import org.openapitools.util.MapperUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class LoginAdapter implements LoginRepository {

    private final LoginEntityRepository loginEntityRepository;

    public LoginAdapter(LoginEntityRepository loginEntityRepository) {
        this.loginEntityRepository = loginEntityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserLogin> findByEmailAndPassword(Login login) {
        return loginEntityRepository.findByEmailAndPassword(login.email(), login.password())
                .map(MapperUtil::entityToUserLoginDto);
    }
}
