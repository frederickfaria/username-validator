package core.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import validator.core.models.entities.RestrictedWord;
import validator.core.models.entities.Username;
import validator.core.repositories.RestrictedWordRepository;
import validator.core.repositories.UsernameRepository;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ffaria on 2/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class JpaRepositoriesTest {

    @Autowired
    RestrictedWordRepository restrictedWordRepository;

    @Autowired
    UsernameRepository usernameRepository;

    private RestrictedWord restrictedWord;

    private Username username;

    @Before
    @Transactional
    @Rollback(false)
    public void setup() {
        restrictedWord = new RestrictedWord();
        restrictedWord.setRestrictedWord("abuse");
        this.restrictedWordRepository.createRestrictedWord(restrictedWord);

        username = new Username();
        username.setUsername("ffaria");
        this.usernameRepository.createUsername(username);
    }


    @Test
    @Transactional
    public void testFind() {
        assertNotNull(this.restrictedWordRepository.findByName(restrictedWord.getRestrictedWord()));
        assertNotNull(this.usernameRepository.findByName(username.getUsername()));
    }

}
