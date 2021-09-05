package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class User {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userName;
    private List ticketList;
    private List archivalTicketList;


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userName='" + userName + '\'' +
                ", ticketList='" + ticketList + '\'' +
                ", archivalTicketList='" + archivalTicketList + '\'' +
                '}';
    }

}