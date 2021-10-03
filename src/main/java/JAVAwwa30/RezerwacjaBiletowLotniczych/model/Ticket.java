package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private User owner;

    @OneToMany
    private List<Flight> flightsOnTicket;

}
