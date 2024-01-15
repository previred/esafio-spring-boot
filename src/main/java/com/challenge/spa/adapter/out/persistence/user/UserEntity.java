package com.challenge.spa.adapter.out.persistence.user;

import com.challenge.spa.application.shared.Role;
import com.challenge.spa.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  private String id = UUID.randomUUID().toString();
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;

  public User toDomain() {
    return new User(
            id,
            username,
            password,
            role
    );
  }

  public static UserEntity fromDomain(User user) {
    var entity = new UserEntity();
    entity.setRole(user.getRole());
    entity.setUsername(user.getUsername());
    entity.setPassword(user.getPassword());
    return entity;
  }

}
