package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.Repository.JPARepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    JPARepository jpaRepository;

    public List<Flight> getFlightsFromOneDestinationToAnother(String startingLocalization, String destination) {
        return jpaRepository.findFlightByStartingLocationAndDestination(startingLocalization, destination);
    }


    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(String startingLocalization, String destination, LocalDateTime dateOfFlight) {
        return getFlightsFromOneDestinationToAnother(startingLocalization, destination).stream().filter(flight -> flight.getDateOfFlight().isAfter(dateOfFlight)).collect(Collectors.toList());
    }

    public List<Flight> findAll() {
        return jpaRepository.findAll();
    }
}
