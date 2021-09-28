package JAVAwwa30.RezerwacjaBiletowLotniczych.repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findFlightByStartingLocationAndDestination(String startingLocation, String destination);

    @Query("SELECT DISTINCT startingLocation FROM Flight")
    List<String> findDistinctStartingLocation();

    @Query("SELECT DISTINCT destination FROM Flight")
    List<String> findDistinctDestination();

}

