package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Journey {

    @OneToMany
    private List<Flight> flightsOnTicket;

}
