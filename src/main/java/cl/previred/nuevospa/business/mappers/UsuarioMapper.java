package cl.previred.nuevospa.business.mappers;

import cl.previred.nuevospa.dto.RolDto;
import cl.previred.nuevospa.dto.UsuarioDto;
import cl.previred.nuevospa.entities.Rol;
import cl.previred.nuevospa.entities.Usuario;

public class UsuarioMapper {

    public static UsuarioDto usuarioToUsuarioDto(Usuario usuario){
        return new UsuarioDto(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getNombre(),
            usuario.getEmail(),
            rolToRolDto(usuario.getRol())
        );
    }

    public static RolDto rolToRolDto(Rol rol){
        return new RolDto(rol.getId(), rol.getNombre());
    }
}
