package validator.rest.mvc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import validator.core.models.entities.RestrictedWord;
import validator.core.services.RestrictedWordService;
import validator.core.services.UsernameService;
import validator.core.services.exception.RestrictedWordException;
import validator.core.services.exception.UsernameExistsException;
import validator.core.services.util.SuggestionList;
import validator.rest.resources.RestrictedWordResource;
import validator.rest.resources.SuggestionListResource;
import validator.rest.resources.asm.RestrictedWordResourceAsm;
import validator.rest.resources.asm.SuggestionListResourceAsm;

import java.util.List;

/**
 * Created by ffaria on 2/17/17.
 */
@Controller
@RequestMapping(value = "/rest")
public class ValidatorController {

    private UsernameService usernameService;

    private RestrictedWordService restrictedWordService;

    @Autowired
    public ValidatorController(UsernameService usernameService, RestrictedWordService restrictedWordService) {
        this.usernameService = usernameService;
        this.restrictedWordService = restrictedWordService;
    }

    @RequestMapping(value = "validate-user-name/{username}", method = RequestMethod.GET)
    public ResponseEntity<SuggestionListResource> validateUserName(@PathVariable String username) {

        try {
            this.usernameService.validateExistingUsername(username);
        } catch (UsernameExistsException usernameExistsException) {
            List<String> suggestions = this.usernameService.generateUsernameSuggestions(username);
            SuggestionListResource suggestionListResource = getSuggestionList(username, suggestions);
            return new ResponseEntity<>(suggestionListResource, HttpStatus.FOUND);
        } catch (RestrictedWordException restrictedWordException) {
            List<String> suggestions = this.usernameService.generateUsernameSuggestions();
            SuggestionListResource suggestionListResource = getSuggestionList(username, suggestions);
            return new ResponseEntity<>(suggestionListResource, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SuggestionListResource getSuggestionList(@PathVariable String username, List<String> suggestions) {
        SuggestionList suggestionList = new SuggestionList();
        suggestionList.setUsernameEntered(username);
        suggestionList.setSuggestedUserNames(suggestions);
        return new SuggestionListResourceAsm().toResource(suggestionList);
    }

    @RequestMapping(value = "save-restricted-word/{restrictedWord}", method = RequestMethod.POST)
    public ResponseEntity<RestrictedWordResource> saveRestrictedWord(@PathVariable String restrictedWord) {

        // validates that the restrictedWord  do not contains numbers or symbols
        if(StringUtils.isAlpha(restrictedWord)) {

            RestrictedWord entry = this.restrictedWordService.findByName(restrictedWord);

            if (entry != null) {
                RestrictedWordResource restrictedWordResource = new RestrictedWordResourceAsm().toResource(entry);
                return new ResponseEntity<>(restrictedWordResource, HttpStatus.FOUND);
            } else {
                RestrictedWord restrictedWordEntry = new RestrictedWord();
                restrictedWordEntry.setRestrictedWord(restrictedWord);

                this.restrictedWordService.createRestrictedWord(restrictedWordEntry);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
