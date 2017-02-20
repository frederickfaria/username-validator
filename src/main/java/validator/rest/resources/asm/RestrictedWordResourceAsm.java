package validator.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import validator.core.models.entities.RestrictedWord;
import validator.rest.mvc.ValidatorController;
import validator.rest.resources.RestrictedWordResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by ffaria on 2/18/17.
 */
public class RestrictedWordResourceAsm extends ResourceAssemblerSupport<RestrictedWord, RestrictedWordResource> {

    public RestrictedWordResourceAsm() {
        super(RestrictedWord.class, RestrictedWordResource.class);
    }

    @Override
    public RestrictedWordResource toResource(RestrictedWord restrictedWord) {

        RestrictedWordResource restrictedWordResource = new RestrictedWordResource();
        restrictedWordResource.setRestrictedWord(restrictedWord.getRestrictedWord());
        restrictedWordResource.add(linkTo(methodOn(ValidatorController.class).saveRestrictedWord(restrictedWord.getRestrictedWord())).withSelfRel());

        return restrictedWordResource;
    }
}
