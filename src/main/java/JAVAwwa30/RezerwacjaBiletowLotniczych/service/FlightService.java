package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidDateOfFlightException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLocationException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(String startingLocation, String destination, String dateOfFlight) throws InvalidLocationException, InvalidDateOfFlightException {
        dateOfFlight = dateOfFlight.trim();
        LocalDateTime date;
        if (dateOfFlight.isBlank()) {
            date = LocalDateTime.now();
        } else {
            date = LocalDateTime.parse(dateOfFlight, DateTimeFormatter.ISO_DATE_TIME);
        }
        flightValidation(startingLocation, destination, date);
        return flightRepository.findFlightByStartingLocationAndDestination(startingLocation, destination).stream().filter(flight -> flight.getDateOfFlight().isAfter(date)).collect(Collectors.toList());
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    private List<Flight> findAllFlightsFromStartingLocation(String startingLocation, LocalDateTime dateOfFlight) {
        return flightRepository.findAllByStartingLocationAndDateOfFlightIsAfter(startingLocation, dateOfFlight);
    }

    private List<Flight> findAllFlightsToDestination(String destination, LocalDateTime dateOfFlight) {
        return flightRepository.findAllByDestinationAndDateOfFlightIsAfter(destination, dateOfFlight);
    }

    public List<List<Flight>> getFlightsWithConnectingFlight(String startingLocation, String destination, String dateOfFlight) throws InvalidLocationException, InvalidDateOfFlightException {
        LocalDateTime date = LocalDateTime.parse(dateOfFlight, DateTimeFormatter.ISO_DATE_TIME);
        flightValidation(startingLocation, destination, date);
        List<Flight> flightsFromStartingLocation = findAllFlightsFromStartingLocation(startingLocation, date);
        List<Flight> flightsToDestination = findAllFlightsToDestination(destination, date);
        List<List<Flight>> result = new ArrayList<>();
        for (Flight flightFromStartingLocation : flightsFromStartingLocation) {
            String destinationOfCheckedFlight = flightFromStartingLocation.getDestination();
            for (Flight potentialConnectingFlight : flightsToDestination) {
                if (destinationOfCheckedFlight.equals(potentialConnectingFlight.getStartingLocation()) && doesOneFlightArriveBeforeAnotherDepart(flightFromStartingLocation, potentialConnectingFlight)) {
                    List<Flight> connectedFlight = new ArrayList<>();
                    connectedFlight.add(flightFromStartingLocation);
                    connectedFlight.add(potentialConnectingFlight);
                    result.add(connectedFlight);
                }
            }
        }
        return result;
    }

    private boolean doesOneFlightArriveBeforeAnotherDepart(Flight firstFlight, Flight secondFlight) {
        return timeOfArrival(firstFlight).isBefore(secondFlight.getDateOfFlight());
    }

    private LocalDateTime timeOfArrival(Flight flight) {
        return flight.getDateOfFlight().plusHours(Long.parseLong(flight.getDurationOfFlight()));
    }

    private boolean startingLocationValidation(String location) {
        List<String> locations = flightRepository.findDistinctStartingLocation();
        return locations.contains(location);
    }

    private boolean destinationValidation(String location) {
        List<String> locations = flightRepository.findDistinctDestination();
        return locations.contains(location);
    }

    private boolean flightValidation(String startingLocation, String destination, LocalDateTime dateOfFlight) throws InvalidLocationException, InvalidDateOfFlightException {
        if (!startingLocationValidation(startingLocation)) {
            throw new InvalidLocationException("Currently no flights from this starting location");
        }
        if (!destinationValidation(destination)) {
            throw new InvalidLocationException("Currently no flights to this destination");
        }
        if (dateOfFlight.isBefore(LocalDateTime.now())) {
            throw new InvalidDateOfFlightException("You cannot select past date for your flight");
        }
        return true;
    }

}
