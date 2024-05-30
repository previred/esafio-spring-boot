package cl.previred.desafio.service.impl;

import cl.previred.desafio.entity.RoleEntity;
import cl.previred.desafio.enums.RoleEnum;
import cl.previred.desafio.repository.RoleRepository;
import cl.previred.desafio.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<RoleEntity> findByName(RoleEnum role) {
        return roleRepository.findByName(role);
    }
}
