package validator.core.services;

import validator.core.models.entities.Username;
import validator.core.services.exception.RestrictedWordException;
import validator.core.services.exception.UsernameExistsException;

import java.util.List;

/**
 * Created by ffaria on 2/18/17.
 */
public interface UsernameService {

    void validateExistingUsername(String username) throws UsernameExistsException, RestrictedWordException;

    Username createUsername(Username username) throws RestrictedWordException, UsernameExistsException;

    List<String> generateUsernameSuggestions(String userName);

    List<String> generateUsernameSuggestions();

}
