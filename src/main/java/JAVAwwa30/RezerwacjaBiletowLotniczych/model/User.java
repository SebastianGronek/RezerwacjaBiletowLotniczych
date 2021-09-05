package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
@Data
public class User {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userName;
    @ManyToMany
    private List <Flight> ticketList;


    public User(String userFirstName, String userName) {
        this.userFirstName = userFirstName;
        this.userName = userName;
    }
}
