package JAVAwwa30.RezerwacjaBiletowLotniczych.repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findFlightByStartingLocationAndDestinationAndDateOfDepartureIsAfter(String startingLocation, String destination, LocalDateTime dateOfDeparture);

    List<Flight> findAllByStartingLocationAndDateOfDepartureIsAfter(String startingLocation, LocalDateTime dateOfDeparture);

    List<Flight> findAllByDestinationAndDateOfDepartureIsAfter(String destination, LocalDateTime dateOfDeparture);

    List<Flight> deleteAllByDateOfDepartureBefore(LocalDateTime dateOfDeparture);


    @Query("SELECT DISTINCT startingLocation FROM Flight where upper(startingLocation) like :input%" )
    List<String> findDistinctStartingLocationStartingWithInput(@Param("input") String input);

    @Query("SELECT DISTINCT destination FROM Flight where UPPER(destination) like :input%" )
    List<String> findDistinctDestinationStartingWithInput(@Param("input") String input);

    @Query("SELECT DISTINCT startingLocation FROM Flight")
    List<String> findDistinctStartingLocation();

    @Query("SELECT DISTINCT destination FROM Flight")
    List<String> findDistinctDestination();

}

