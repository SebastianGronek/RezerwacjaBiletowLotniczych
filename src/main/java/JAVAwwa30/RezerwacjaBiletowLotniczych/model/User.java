package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="users")
public class User {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userName;
    @ManyToMany
    private List <Flight> ticketList;

}
