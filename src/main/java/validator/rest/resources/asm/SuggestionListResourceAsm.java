package validator.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import validator.core.services.util.SuggestionList;
import validator.rest.mvc.ValidatorController;
import validator.rest.resources.SuggestionListResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by ffaria on 2/18/17.
 */
public class SuggestionListResourceAsm extends ResourceAssemblerSupport<SuggestionList, SuggestionListResource> {

    public SuggestionListResourceAsm() {
        super(SuggestionList.class, SuggestionListResource.class);
    }

    @Override
    public SuggestionListResource toResource(SuggestionList suggestionList) {

        SuggestionListResource suggestionListResource = new SuggestionListResource();
        suggestionListResource.setSuggestedUserNames(suggestionList.getSuggestedUserNames());
        suggestionListResource.add(linkTo(methodOn(ValidatorController.class).validateUserName(suggestionList.getUsernameEntered())).withSelfRel());

        return suggestionListResource;
    }
}
