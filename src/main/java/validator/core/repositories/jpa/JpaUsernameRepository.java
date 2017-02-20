package validator.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import validator.core.models.entities.Username;
import validator.core.repositories.UsernameRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by ffaria on 2/18/17.
 */
@Repository
public class JpaUsernameRepository implements UsernameRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Username findByName(String username) {
        try {
            return em.createQuery("SELECT t FROM Username t where t.username = :value1", Username.class)
                    .setParameter("value1", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Username createUsername(Username username) {
        em.persist(username);
        return username;
    }

}
