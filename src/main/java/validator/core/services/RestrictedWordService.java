package validator.core.services;

import validator.core.models.entities.RestrictedWord;

/**
 * Created by ffaria on 2/18/17.
 */
public interface RestrictedWordService {

    RestrictedWord findByName(String restrictedWord);

    boolean containsRestrictedWord(String username);

    RestrictedWord createRestrictedWord(RestrictedWord restrictedWord);

}
