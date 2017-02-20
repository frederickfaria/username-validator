package validator.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import validator.core.models.entities.RestrictedWord;
import validator.core.repositories.RestrictedWordRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by ffaria on 2/18/17.
 */
@Repository
public class JpaRestrictedWordRepository implements RestrictedWordRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public RestrictedWord findByName(String restrictedWord) {
        try {
            RestrictedWord entity = em.createQuery("SELECT t FROM RestrictedWord t where t.restrictedWord like :value1", RestrictedWord.class)
                    .setParameter("value1", "%" + restrictedWord + "%").getSingleResult();
            return entity;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public boolean containsRestrictedWord(String username) {
        Query query = em.createNativeQuery("select id,restrictedword from restrictedword where ? ~ restrictedword;");
        query.setParameter(1, username);
        if(query.getResultList().size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public RestrictedWord createRestrictedWord(RestrictedWord restrictedWord) {
        em.persist(restrictedWord);
        return restrictedWord;
    }
}
