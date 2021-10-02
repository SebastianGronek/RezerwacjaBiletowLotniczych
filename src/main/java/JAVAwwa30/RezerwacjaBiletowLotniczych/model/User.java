package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
@Data
@JsonIgnoreProperties(value= {"ticketList"})
public class User {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String login;
    @ManyToMany
    private List <Flight> ticketList;


    public User(String userFirstName, String userLastName, String login, List<Flight>  ticketList) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.login = login;
        this.ticketList = ticketList;
    }
}
