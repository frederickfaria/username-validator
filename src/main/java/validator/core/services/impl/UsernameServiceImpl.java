package validator.core.services.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.core.models.entities.Username;
import validator.core.repositories.RestrictedWordRepository;
import validator.core.repositories.UsernameRepository;
import validator.core.services.UsernameService;
import validator.core.services.exception.RestrictedWordException;
import validator.core.services.exception.UsernameExistsException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ffaria on 2/18/17.
 */
@Service
@Transactional
public class UsernameServiceImpl implements UsernameService {

    public static final int SUGGESTION_LIST_AMOUNT = 14;

    private Random rand = new Random();

    private UsernameRepository usernameRepository;

    private RestrictedWordRepository restrictedWordRepository;


    @Autowired
    public UsernameServiceImpl(UsernameRepository usernameRepository, RestrictedWordRepository restrictedWordRepository) {
        this.usernameRepository = usernameRepository;
        this.restrictedWordRepository = restrictedWordRepository;
    }

    /**
     * Validates if an username exists in the database
     *
     * @param username user name to validate
     */
    @Override
    public void validateExistingUsername(String username) throws UsernameExistsException, RestrictedWordException {

        validateUsername(username);

        // Searching if is an restricted word
        if (this.restrictedWordRepository.containsRestrictedWord(username)) {
            // it is or contails a restricted word
            throw new RestrictedWordException();
        }
    }

    @Override
    public List<String> generateUsernameSuggestions(String username) {

        String suggestedUsernameNumeric;
        int maxForIterations = UsernameServiceImpl.SUGGESTION_LIST_AMOUNT;
        List<String> suggestionList = new ArrayList<>(UsernameServiceImpl.SUGGESTION_LIST_AMOUNT);

        if(username == null){
            username = RandomStringUtils.randomAlphabetic(6).toLowerCase();
        }

        if (StringUtils.contains(username, ".")) {
            username = StringUtils.remove(username, ".");

            try {
                validateExistingUsername(username);
                suggestionList.add(username);
                maxForIterations--;
            } catch (UsernameExistsException | RestrictedWordException e) {
            }
        }

        for (int i = 0; i < maxForIterations; i++) {

            try {
                suggestedUsernameNumeric = username + String.format("%04d", generateRandomNumber());
                validateUsername(suggestedUsernameNumeric);
                if (suggestionList.contains(suggestedUsernameNumeric)) {
                    throw new UsernameExistsException();
                }
            } catch (UsernameExistsException e) {
                i--;
                continue;
            }

            suggestionList.add(suggestedUsernameNumeric);
        }

        Collections.sort(suggestionList);

        return suggestionList;
    }

    @Override
    public List<String> generateUsernameSuggestions() {
        return generateUsernameSuggestions(null);
    }

    @Override
    public Username createUsername(Username username) throws RestrictedWordException, UsernameExistsException {
        validateExistingUsername(username.getUsername());
        usernameRepository.createUsername(username);
        return username;
    }

    protected int generateRandomNumber() {
        return rand.nextInt(10000);
    }

    private void validateUsername(String username) throws UsernameExistsException {
        // Searching if the username exists in database
        Username existingUsername = this.usernameRepository.findByName(username);

        if (existingUsername != null) {
            // User name exists
            throw new UsernameExistsException();
        }
    }
}
