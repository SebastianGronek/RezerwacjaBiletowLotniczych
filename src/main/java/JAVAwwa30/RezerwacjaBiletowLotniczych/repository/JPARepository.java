package JAVAwwa30.RezerwacjaBiletowLotniczych.repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPARepository extends JpaRepository<Flight, Long> {
    List<Flight> findFlightByStartingLocationAndDestination(String startingLocation, String destination);
}

