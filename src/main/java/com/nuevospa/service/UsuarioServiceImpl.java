package com.nuevospa.service;

import com.nuevospa.dto.UsuarioDTO;
import com.nuevospa.entity.UsuarioEntity;
import com.nuevospa.interfaces.UsuarioService;
import com.nuevospa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public boolean validarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = usuarioRepository.findByNombreAndPassword(usuarioDTO.getNombre(), usuarioDTO.getPassword());
        return usuario != null;
    }

    @Override
    public List<UsuarioDTO> listar() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean validarNombre(String nombre) {
        UsuarioEntity usuario = usuarioRepository.findByNombre(nombre);
        return usuario != null;
    }

    @Override
    public void insertarUsuario(UsuarioDTO usuarioDTO) {
        usuarioRepository.save(new UsuarioEntity(usuarioDTO.getNombre(), usuarioDTO.getPassword()));
    }
    
    public void eliminarUsuarioId(Long id) {
    	usuarioRepository.deleteById(id);
    }
    
    public Optional<UsuarioDTO> actualizarUsuario(UsuarioDTO usuarioDTO) {
        // Verifica si el usuario con el ID proporcionado existe
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(usuarioDTO.getId());

        if (optionalUsuario.isPresent()) {
            // Actualiza los datos del usuario existente con la información del DTO
            UsuarioEntity usuarioActualizado = optionalUsuario.get();
            usuarioActualizado.setNombre(usuarioDTO.getNombre());
            usuarioActualizado.setPassword(usuarioDTO.getPassword());

            // Guarda la entidad actualizada en el repositorio
            usuarioRepository.save(usuarioActualizado);

            // Convierte la entidad actualizada a un DTO y lo devuelve
            return Optional.of(UsuarioDTO.fromEntity(usuarioActualizado));
        } else {
            // Si el usuario no existe, devuelve un Optional vacío
            return Optional.empty();
        }
    }
}
