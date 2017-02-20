package validator.core.models.entities;

import javax.persistence.*;

/**
 * Created by ffaria on 2/17/17.
 */
@Entity
@Table(indexes = { @Index(columnList = "username") })
public class Username {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
