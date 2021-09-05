package JAVAwwa30.RezerwacjaBiletowLotniczych.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String startingLocation;
    private String destination;
    private String durationOfFlight;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", startingLocalization='" + startingLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", durationOfFlight='" + durationOfFlight + '\'' +
                '}';
    }
}
