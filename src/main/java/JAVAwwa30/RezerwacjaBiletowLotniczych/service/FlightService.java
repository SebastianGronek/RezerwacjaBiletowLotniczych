package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.JPARepository;
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

    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(String startingLocalization, String destination, String dateOfFlight) {
        if (dateOfFlight == null) {
            return jpaRepository.findFlightByStartingLocationAndDestination(startingLocalization, destination);
        }
        LocalDateTime date = LocalDateTime.parse(dateOfFlight);
        return jpaRepository.findFlightByStartingLocationAndDestination(startingLocalization, destination).stream().filter(flight -> flight.getDateOfFlight().isAfter(date)).collect(Collectors.toList());
    }

    public List<Flight> findAll() {
        return jpaRepository.findAll();
    }
}
