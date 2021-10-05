package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLocationException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Journey;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;


    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(String startingLocation, String destination, String dateOfDeparture) {
        dateOfDeparture = dateOfDeparture.trim();
        LocalDateTime date;
        if (dateOfDeparture.isBlank()) {
            date = LocalDateTime.now();
        } else {
            date = LocalDateTime.parse(dateOfDeparture, DateTimeFormatter.ISO_DATE_TIME);
        }
        flightValidation(startingLocation, destination, date);
        return flightRepository.findFlightByStartingLocationAndDestinationAndDateOfDepartureIsAfter(startingLocation, destination, date);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    private List<Flight> findAllFlightsFromStartingLocation(String startingLocation, LocalDateTime dateOfFlight) {
        return flightRepository.findAllByStartingLocationAndDateOfDepartureIsAfter(startingLocation, dateOfFlight);
    }

    private List<Flight> findAllFlightsToDestination(String destination, LocalDateTime dateOfFlight) {
        return flightRepository.findAllByDestinationAndDateOfDepartureIsAfter(destination, dateOfFlight);
    }

    public List<Journey> getFlightsWithConnectingFlight(String startingLocation, String destination, String dateOfFlight) {
        LocalDateTime date = LocalDateTime.parse(dateOfFlight, DateTimeFormatter.ISO_DATE_TIME);
        flightValidation(startingLocation, destination, date);
        List<Flight> flightsFromStartingLocation = findAllFlightsFromStartingLocation(startingLocation, date);
        List<Flight> flightsToDestination = findAllFlightsToDestination(destination, date);
        List<Journey> result = new ArrayList<>();
        for (Flight flightFrom : flightsFromStartingLocation) {
            for (Flight flightTo : flightsToDestination) {
                if (checkPotentialConnectingFlight(flightFrom, flightTo)) {
                    Journey journey = addFlightsToTicket(flightFrom, flightTo);
                    result.add(journey);
                }
            }
        }
        return result;
    }

    private boolean checkPotentialConnectingFlight(Flight flightFromStartingLocation, Flight checkedFlight) {
        return flightFromStartingLocation.getDestination().equals(checkedFlight.getStartingLocation()) && doesOneFlightArriveBeforeAnotherDepart(flightFromStartingLocation, checkedFlight);
    }

    private Journey addFlightsToTicket(Flight flightFromStartingLocation, Flight potentialConnectingFlight) {
        Journey connectedFlight = new Journey();
        connectedFlight.getFlightsOnTicket().add(flightFromStartingLocation);
        connectedFlight.getFlightsOnTicket().add(potentialConnectingFlight);
        return connectedFlight;
    }

    private boolean doesOneFlightArriveBeforeAnotherDepart(Flight firstFlight, Flight secondFlight) {
        return timeOfArrival(firstFlight).isBefore(secondFlight.getDateOfDeparture());
    }

    private LocalDateTime timeOfArrival(Flight flight) {
        return flight.getDateOfDeparture().plusHours(Long.parseLong(flight.getDurationOfFlight()));
    }

    private boolean startingLocationValidation(String location) {
        List<String> locations = flightRepository.findDistinctStartingLocation();
        return locations.contains(location);
    }

    private boolean destinationValidation(String location) {
        List<String> locations = flightRepository.findDistinctDestination();
        return locations.contains(location);
    }

    private void flightValidation(String startingLocation, String destination, LocalDateTime dateOfDeparture) {
        if (!startingLocationValidation(startingLocation)) {
            throw new InvalidLocationException("Currently no flights from this starting location");
        }
        if (!destinationValidation(destination)) {
            throw new InvalidLocationException("Currently no flights to this destination");
        }
       /* if (dateOfFlight.isBefore(LocalDateTime.now())) {
            throw new InvalidDateOfFlightException("You cannot select past date for your flight");
        }*/
    }

    public List<String> findStartingLocationForAutocomplete(String input) {
        return flightRepository.findDistinctStartingLocationStartingWithInput(input.toUpperCase().stripLeading());
    }

    public List<String> findDestinationForAutocomplete(String input) {
        return flightRepository.findDistinctDestinationStartingWithInput(input.toUpperCase().stripLeading());
    }

}
