package cl.previred.nuevospa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.previred.nuevospa.entities.Tarea;

@Repository
@Transactional
public class TareaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Tarea> findAll(String username) {
        try{
            return entityManager.createQuery("SELECT t FROM Tarea t WHERE t.usuario.username = :username", Tarea.class)
                .setParameter("username", username)
                .getResultList();
        }catch(NoResultException nre){
            return null;
        }
    }

    public Tarea findById(String username, Long id) {
        try{
            return entityManager.createQuery("SELECT t FROM Tarea t WHERE t.usuario.username = :username AND t.id = :id", Tarea.class)
                .setParameter("username", username)
                .setParameter("id", id)
                .getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    public void save(Tarea pruebaEntidad) {
        entityManager.persist(pruebaEntidad);
    }

    public void delete(Tarea tareaEntidad) {
        entityManager.remove(tareaEntidad);
    }

    public void update(Tarea tareaEntidad) {
        entityManager.merge(tareaEntidad);
    }

}
