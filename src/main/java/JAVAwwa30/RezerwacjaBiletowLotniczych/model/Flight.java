package JAVAwwa30.RezerwacjaBiletowLotniczych.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String startingLocation;
    private String destination;
    private String durationOfFlight;
    private LocalDateTime dateOfFlight;


    public Flight(String startingLocation, String destination, String durationOfFlight, LocalDateTime dateOfFlight) {
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.durationOfFlight = durationOfFlight;
        this.dateOfFlight = dateOfFlight;
    }
}
