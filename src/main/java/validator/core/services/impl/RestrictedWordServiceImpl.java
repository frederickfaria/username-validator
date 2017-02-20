package validator.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.core.models.entities.RestrictedWord;
import validator.core.repositories.RestrictedWordRepository;
import validator.core.services.RestrictedWordService;

import javax.transaction.Transactional;

/**
 * Created by ffaria on 2/18/17.
 */
@Service
@Transactional
public class RestrictedWordServiceImpl implements RestrictedWordService {

    @Autowired
    private RestrictedWordRepository restrictedWordRepository;

    @Override
    public RestrictedWord findByName(String restrictedWord) {
        return restrictedWordRepository.findByName(restrictedWord);
    }

    @Override
    public boolean containsRestrictedWord(String username) {
        return restrictedWordRepository.containsRestrictedWord(username);
    }

    @Override
    public RestrictedWord createRestrictedWord(RestrictedWord restrictedWord) {
        RestrictedWord persistedRestrictedWord = this.restrictedWordRepository.createRestrictedWord(restrictedWord);
        return persistedRestrictedWord;
    }
}
