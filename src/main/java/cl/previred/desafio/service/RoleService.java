package cl.previred.desafio.service;

import cl.previred.desafio.entity.RoleEntity;
import cl.previred.desafio.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {
    RoleEntity saveRole(RoleEntity role);

    Optional<RoleEntity> findByName(RoleEnum role);
}