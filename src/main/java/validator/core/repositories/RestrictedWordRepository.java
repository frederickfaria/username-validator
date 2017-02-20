package validator.core.repositories;

import validator.core.models.entities.RestrictedWord;

/**
 * Created by ffaria on 2/18/17.
 */
public interface RestrictedWordRepository {
    RestrictedWord findByName(String restrictedWord);

    boolean containsRestrictedWord(String username);

    RestrictedWord createRestrictedWord(RestrictedWord restrictedWord);
}
