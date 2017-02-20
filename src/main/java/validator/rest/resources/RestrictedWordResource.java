package validator.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by ffaria on 2/18/17.
 */
public class RestrictedWordResource extends ResourceSupport {

    private String restrictedWord;

    public String getRestrictedWord() {
        return restrictedWord;
    }

    public void setRestrictedWord(String restrictedWord) {
        this.restrictedWord = restrictedWord;
    }
}
