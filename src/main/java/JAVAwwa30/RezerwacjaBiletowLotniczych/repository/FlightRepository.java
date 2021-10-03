package JAVAwwa30.RezerwacjaBiletowLotniczych.repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findFlightByStartingLocationAndDestination(String startingLocation, String destination);

    List<Flight> findAllByStartingLocationAndDateOfFlightIsAfter(String startingLocation, LocalDateTime dateOfFlight);

    List<Flight> findAllByDestinationAndDateOfFlightIsAfter(String destination, LocalDateTime dateOfFlight);

    List<Flight> deleteAllByDateOfFlightBefore(LocalDateTime dateOfFlight);

    @Query("SELECT DISTINCT startingLocation FROM Flight")
    List<String> findDistinctStartingLocation();

    @Query("SELECT DISTINCT destination FROM Flight")
    List<String> findDistinctDestination();

}

