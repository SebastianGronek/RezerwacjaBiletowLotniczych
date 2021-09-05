package JAVAwwa30.RezerwacjaBiletowLotniczych.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.List;


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
    @ManyToMany
    private List<User> passengers;
    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", startingLocalization='" + startingLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", durationOfFlight='" + durationOfFlight + '\'' +
                ", dateOfFlight='" + dateOfFlight + '\'' +
                '}';
    }
}
