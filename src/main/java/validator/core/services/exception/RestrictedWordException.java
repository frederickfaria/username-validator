package validator.core.services.exception;

/**
 * Created by ffaria on 2/19/17.
 */
public class RestrictedWordException extends Exception {
    public RestrictedWordException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestrictedWordException(String message) {
        super(message);
    }

    public RestrictedWordException() {
        super();
    }
}
