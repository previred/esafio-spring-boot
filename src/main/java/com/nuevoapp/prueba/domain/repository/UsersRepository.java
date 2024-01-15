package com.nuevoapp.prueba.domain.repository;

import com.nuevoapp.prueba.domain.model.entity.UserEntity;
import com.nuevoapp.prueba.domain.model.enums.UserStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

public interface UsersRepository extends JpaRepository<UserEntity, String> {
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity SET status = :status, lastLoginDate = :now where email = :email")
    void updateUserStatusAndLastLoginDateByEmail(UserStatusEnum status, ZonedDateTime now, String email);
}
