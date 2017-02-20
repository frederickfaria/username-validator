package validator.core.services.exception;

/**
 * Created by ffaria on 2/19/17.
 */
public class UsernameExistsException extends Exception {

    public UsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException() {
        super();
    }

}
