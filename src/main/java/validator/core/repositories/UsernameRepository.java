package validator.core.repositories;

import validator.core.models.entities.Username;

/**
 * Created by ffaria on 2/18/17.
 */
public interface UsernameRepository {

    Username findByName(String username);

    Username createUsername(Username username);

}
