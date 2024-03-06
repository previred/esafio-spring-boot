package cl.previred.nuevospa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.previred.nuevospa.entities.Usuario;

@Repository
@Transactional
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Usuario findByUsername(String username) {
        try{
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }

    public List<Usuario> findAll() {
        try{
            return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
        }catch(NoResultException nre){
            return null;
        }
    }

    public Usuario findById(Integer id) {
        return entityManager.find(Usuario.class, id);
    }
}
