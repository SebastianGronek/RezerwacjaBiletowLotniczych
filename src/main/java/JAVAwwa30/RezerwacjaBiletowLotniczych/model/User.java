package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "login"), @UniqueConstraint(columnNames = "email")})
//@JsonIgnoreProperties(value= {"ticketList"})
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String login;
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_userId"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String userFirstName, String userLastName) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public User(String userFirstName, String userLastName, String login) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.login = login;
    }

    public User(Long userId, String userFirstName, String userLastName, String login) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.login = login;
    }

    public User(String userFirstName, String userLastName, String login, String email, String password) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
