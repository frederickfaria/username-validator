package validator.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffaria on 2/18/17.
 */
public class SuggestionListResource extends ResourceSupport {

    private List<String> suggestedUserNames = new ArrayList<>();

    public List<String> getSuggestedUserNames() {
        return suggestedUserNames;
    }

    public void setSuggestedUserNames(List<String> suggestedUserNames) {
        this.suggestedUserNames = suggestedUserNames;
    }
}
