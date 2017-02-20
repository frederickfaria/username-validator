package validator.core.services.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffaria on 2/18/17.
 */
public class SuggestionList {

    private String usernameEntered;

    private List<String> suggestedUserNames = new ArrayList<>();

    public String getUsernameEntered() {
        return usernameEntered;
    }

    public void setUsernameEntered(String usernameEntered) {
        this.usernameEntered = usernameEntered;
    }

    public List<String> getSuggestedUserNames() {
        return suggestedUserNames;
    }

    public void setSuggestedUserNames(List<String> suggestedUserNames) {
        this.suggestedUserNames = suggestedUserNames;
    }
}
