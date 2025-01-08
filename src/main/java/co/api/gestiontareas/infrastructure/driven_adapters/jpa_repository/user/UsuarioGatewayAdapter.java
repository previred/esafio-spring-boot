package co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.user;

import co.api.gestiontareas.domain.model.user.User;
import co.api.gestiontareas.domain.model.user.repository.UserGateway;
import co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.helper.AdapterOperations;
import co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.user.entities.UsuarioEntity;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioGatewayAdapter extends AdapterOperations<User, UsuarioEntity, Long, UsuarioDataRepository>
 implements UserGateway
{

    public UsuarioGatewayAdapter(UsuarioDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public User getUser(String username) {
        return toEntity(repository.findByUsername(username));
    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
