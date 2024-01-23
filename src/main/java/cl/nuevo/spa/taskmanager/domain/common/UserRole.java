package cl.nuevo.spa.taskmanager.domain.common;

import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.CREATE_ONE_TASK;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.CREATE_ONE_USER;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.DELETE_ONE_TASK;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.DELETE_ONE_USER;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.READ_ALL_TASK;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.READ_ALL_USER;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.READ_ONE_TASK;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.READ_ONE_USER;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.UPDATE_ONE_TASK;
import static cl.nuevo.spa.taskmanager.domain.common.RolePermission.UPDATE_ONE_USER;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
  ROLE_USER(
      List.of(
          READ_ALL_USER,
          READ_ONE_USER,
          READ_ALL_TASK,
          READ_ONE_TASK,
          CREATE_ONE_TASK,
          UPDATE_ONE_TASK,
          DELETE_ONE_TASK)),
  ROLE_ADMIN(
      List.of(
          READ_ALL_TASK,
          READ_ONE_TASK,
          CREATE_ONE_TASK,
          UPDATE_ONE_TASK,
          DELETE_ONE_TASK,
          READ_ALL_USER,
          READ_ONE_USER,
          CREATE_ONE_USER,
          UPDATE_ONE_USER,
          DELETE_ONE_USER));

  private final List<RolePermission> permissions;
}
