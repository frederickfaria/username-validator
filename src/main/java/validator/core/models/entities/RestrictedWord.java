package validator.core.models.entities;

import javax.persistence.*;

/**
 * Created by ffaria on 2/18/17.
 */
@Entity
@Table(indexes = { @Index(columnList = "restrictedWord") })
public class RestrictedWord {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    protected String restrictedWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestrictedWord() {
        return restrictedWord;
    }

    public void setRestrictedWord(String restrictedWord) {
        this.restrictedWord = restrictedWord;
    }
}
