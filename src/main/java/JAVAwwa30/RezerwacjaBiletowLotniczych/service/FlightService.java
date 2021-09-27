package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(String startingLocation, String destination, String dateOfFlight) {
        if (!startingLocationValidation(startingLocation)) {
            throw new IllegalArgumentException("Currently no flights form this starting location");
        }
        if (!destinationValidation(destination)) {
            throw new IllegalArgumentException("Currently no flights to this destination");
        }
        if (dateOfFlight == null) {
            return flightRepository.findFlightByStartingLocationAndDestination(startingLocation, destination);
        }
        LocalDateTime date = LocalDateTime.parse(dateOfFlight);
        return flightRepository.findFlightByStartingLocationAndDestination(startingLocation, destination).stream().filter(flight -> flight.getDateOfFlight().isAfter(date)).collect(Collectors.toList());
    }

    private boolean startingLocationValidation(String location) {
        List<String> locations = flightRepository.findDistinctStartingLocation();
        return locations.contains(location);
    }

    private boolean destinationValidation(String location) {
        List<String> locations = flightRepository.findDistinctDestination();
        return locations.contains(location);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }
}
