package com.spa.crud.service.impl;

import com.spa.crud.dto.RolesDTO;
import com.spa.crud.dto.UsuariosDTO;
import com.spa.crud.exception.CrudException;
import com.spa.crud.jwt.JwtService;
import com.spa.crud.mapper.UsuariosMapper;
import com.spa.crud.model.Roles;
import com.spa.crud.model.Usuarios;
import com.spa.crud.repository.RolesRepository;
import com.spa.crud.repository.UsuariosRepository;
import com.spa.crud.service.UsuariosService;
import com.spa.crud.utils.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    private static final Logger logger = LoggerFactory.getLogger(UsuariosServiceImpl.class);

    @Autowired
    private UsuariosRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuariosMapper usuariosMapper;

    @Override
    public UsuariosDTO loadUserByUser(Usuarios user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            Usuarios userModel = userRepository.getByUsername(user.getUsername());
            UsuariosDTO userDto = modelToDtoUser(userModel);
            String token = jwtService.getToken(userModel);
            userDto.setToken(token);
            return userDto;
        }catch (ExpiredJwtException e){
            logger.error(Constants.TOKEN_EXPIRED_MSG);
            throw new CrudException(Constants.TOKEN_EXPIRED_MSG);
        }catch (AuthenticationException e){
            logger.error(Constants.BAD_CREDENTIALS_MSG);
            throw new CrudException(e.getMessage());
        }

    }

    @Override
    public void createNewUser(Usuarios user) {
        Roles rol = rolesRepository.findRolByName(user.getRole().getNombre());
        if(rol != null) {
            user.setRole(rol);
            userRepository.save(user);
        }

    }

    @Override
    public List<UsuariosDTO> getAllUsers() {
        return userRepository.findAll().stream().map(usuariosMapper::userToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(String username) {
        checkUsername(username);
        Long id = userRepository.getIdByUsername(username);
        userRepository.deleteByIdUsuario(id);
    }

    private void checkUsername(String username){
        if (userRepository.findByUsername(username).isEmpty()){
            logger.error(Constants.USER_NOT_FOUND);
            throw new CrudException(Constants.USER_NOT_FOUND);
        }
    }

    private UsuariosDTO modelToDtoUser(Usuarios modelUser) {
        UsuariosDTO user = new UsuariosDTO();
        user.setNombre(modelUser.getNombre());
        user.setMail(modelUser.getMail());
        RolesDTO rolesDTO = new RolesDTO();
            rolesDTO.setIdRoles(modelUser.getRole().getIdRol());
            rolesDTO.setNombre(modelUser.getRole().getNombre());
        user.setRole(rolesDTO);
        user.setUsername(modelUser.getUsername());

        return user;
    }


}
