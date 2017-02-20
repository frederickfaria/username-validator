package validator.core.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import validator.core.models.entities.RestrictedWord;
import validator.core.models.entities.Username;
import validator.core.repositories.RestrictedWordRepository;
import validator.core.repositories.UsernameRepository;
import validator.core.services.exception.RestrictedWordException;
import validator.core.services.exception.UsernameExistsException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by ffaria on 2/19/17.
 */
public class UsernameServiceImplTest {

    @Mock
    private UsernameRepository usernameRepository;

    @Mock
    private RestrictedWordRepository restrictedWordRepository;

    @InjectMocks
    UsernameServiceImpl usernameService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UsernameExistsException.class)
    public void validateExistingUsernameUsernameExists() throws Exception {
        Username username = new Username();
        username.setId(1L);
        username.setUsername("ffaria");

        when(usernameRepository.findByName("ffaria")).thenReturn(username);

        this.usernameService.validateExistingUsername("ffaria");
    }

    @Test(expected = RestrictedWordException.class)
    public void validateExistingUsernameUsernameNonExistsRestrictedWord() throws Exception {
        RestrictedWord restrictedWord = new RestrictedWord();
        restrictedWord.setId(1L);
        restrictedWord.setRestrictedWord("abuse");

        when(restrictedWordRepository.findByName("abuse")).thenReturn(restrictedWord);
        when(restrictedWordRepository.containsRestrictedWord("abuse")).thenReturn(true);

        this.usernameService.validateExistingUsername("abuse");
    }

    @Test
    public void generateUsernameSuggestions() throws Exception {
        List<String> suggestions = this.usernameService.generateUsernameSuggestions("ffaria");
        assertEquals(UsernameServiceImpl.SUGGESTION_LIST_AMOUNT, suggestions.size());
    }

    @Test
    public void generateUsernameSuggestionsExistingSuggestion() throws Exception {

        Username username = new Username();
        username.setId(1L);
        username.setUsername("frederickfaria");

        when(usernameRepository.findByName("frederickfaria")).thenReturn(username);

        List<String> suggestions = this.usernameService.generateUsernameSuggestions("frederick.faria");

        assertEquals(UsernameServiceImpl.SUGGESTION_LIST_AMOUNT, suggestions.size());
    }

    @Test
    public void generateUsernameSuggestionsAfterRestrictedWord() throws Exception {

        List<String> suggestions = this.usernameService.generateUsernameSuggestions();

        assertEquals(UsernameServiceImpl.SUGGESTION_LIST_AMOUNT, suggestions.size());
    }

    @Test
    public void generateUsernameSuggestionsNonExistingSuggestion() throws Exception {

        List<String> suggestions = this.usernameService.generateUsernameSuggestions("frederick.faria");

        assertEquals(UsernameServiceImpl.SUGGESTION_LIST_AMOUNT, suggestions.size());
    }

    @Test
    public void generateUsernameSuggestionsExistingNumericSuggestion() throws Exception {

        Username username = new Username();
        username.setId(1L);
        username.setUsername("frederickfaria2463");

        UsernameServiceImpl mockedUsernameService = Mockito.spy(this.usernameService);

        when(usernameRepository.findByName("frederickfaria2463")).thenReturn(username);

        when(mockedUsernameService.generateRandomNumber()).thenReturn(2463, 3942, 3402, 2463, 2344, 5312, 7676, 2352, 3341, 7553, 7435, 7542, 4566, 2231, 4334);

        List<String> suggestions = mockedUsernameService.generateUsernameSuggestions("frederick.faria");

        assertEquals(UsernameServiceImpl.SUGGESTION_LIST_AMOUNT, suggestions.size());
    }
}