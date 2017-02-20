package validator.mvc.rest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import validator.core.models.entities.RestrictedWord;
import validator.core.services.RestrictedWordService;
import validator.core.services.UsernameService;
import validator.core.services.exception.RestrictedWordException;
import validator.core.services.exception.UsernameExistsException;
import validator.rest.mvc.ValidatorController;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by ffaria on 2/18/17.
 */
public class validatorControllerTest {

    @InjectMocks
    private ValidatorController controller;

    @Mock
    private UsernameService usernameService;

    @Mock
    private RestrictedWordService restrictedWordService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void validateNonExistingNonRestrictedWordUserName() throws Exception {

        mockMvc.perform(get("/rest/validate-user-name/ffaria"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void validateExistingUserName() throws Exception {

        doThrow(UsernameExistsException.class).when(this.usernameService).validateExistingUsername("ffaria");
        when(usernameService.generateUsernameSuggestions("ffaria")).thenReturn(Arrays.asList("ffaria2323", "ffaria3453", "ffaria5354", "ffaria6834"));

        mockMvc.perform(get("/rest/validate-user-name/ffaria"))
                //.andDo(print())
                .andExpect(jsonPath("$.suggestedUserNames[*]", containsInAnyOrder("ffaria2323", "ffaria3453", "ffaria5354", "ffaria6834")))
                .andExpect(status().isFound());
    }

    @Test
    public void validateUserNameRestrictedWord() throws Exception {

        doThrow(RestrictedWordException.class).when(this.usernameService).validateExistingUsername("abuse");

        mockMvc.perform(get("/rest/validate-user-name/abuse"))
                //.andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void saveNonExistingRestrictedWord() throws Exception {
        when(restrictedWordService.findByName("killer")).thenReturn(null);

        mockMvc.perform(post("/rest/save-restricted-word/killer"))
                //.andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void saveExistingRestrictedWord() throws Exception {

        RestrictedWord restrictedWord = new RestrictedWord();

        restrictedWord.setId(1L);
        restrictedWord.setRestrictedWord("abuse");

        when(restrictedWordService.findByName("abuse")).thenReturn(restrictedWord);

        mockMvc.perform(post("/rest/save-restricted-word/abuse"))
                //.andDo(print())
                .andExpect(jsonPath("$.restrictedWord", is("abuse")))
                .andExpect(status().isFound());

    }

    @Test
    public void saveNonValidRestrictedWord() throws Exception {

        mockMvc.perform(post("/rest/save-restricted-word/abuse111"))
                //.andDo(print())
                .andExpect(status().isNotAcceptable());

    }
}
