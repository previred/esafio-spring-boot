package com.test.moveapps.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.moveapps.domain.entity.Role;
import com.test.moveapps.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    @NotNull(message = "${value.not.null}")
    @NotEmpty(message = "${value.not.empty}")
    private String username;

    private String name;

    @NotNull(message = "${value.not.null}")
    @NotEmpty(message = "${value.not.empty}")
    private String password;

    private String token;

    private boolean isActive;

    private LocalDateTime lastLogin;

    private List<RoleDto> roles;


    public UserDto() {
    }

    public UserDto(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public UserDto(Long id, String username, String name, String password, boolean active,
                   LocalDateTime lastLogin,
                   List<RoleDto> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.isActive = active;
        this.lastLogin = lastLogin;
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<RoleDto> getRoles() {
        return this.roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public static User convert(UserDto userDto, String passwordEncoded, Role role) {
        final User user = new User();

        if(Objects.isNull(userDto) || Objects.isNull(passwordEncoded)  || Objects.isNull(role)) {
            throw new IllegalArgumentException("arg.invalidos");
        }

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoded);
        user.setActive(true);
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        return user;
    }

    public static UserDto convert(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setActive(user.getActive());
        if(user.getLastLogin()!=null) {
            userDto.setLastLogin(user.getLastLogin()
                    .toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
        }

        return userDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", active=" + this.isActive +
                ", lastLogin=" + this.lastLogin +
                ", roles=" + this.roles +
                '}';
    }
}
