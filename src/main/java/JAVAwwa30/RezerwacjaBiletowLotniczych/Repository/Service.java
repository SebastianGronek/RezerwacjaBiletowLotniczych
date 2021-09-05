package JAVAwwa30.RezerwacjaBiletowLotniczych.Repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;

import java.util.Optional;

public interface Service {

        Optional<Flight> findById(Long id);


}
