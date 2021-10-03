package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidDateOfFlightException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLocationException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Ticket;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;


    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(String startingLocation, String destination, String dateOfFlight) {
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

    public List<Ticket> getFlightsWithConnectingFlight(String startingLocation, String destination, String dateOfFlight) {
        LocalDateTime date = LocalDateTime.parse(dateOfFlight, DateTimeFormatter.ISO_DATE_TIME);
        flightValidation(startingLocation, destination, date);
        List<Flight> flightsFromStartingLocation = findAllFlightsFromStartingLocation(startingLocation, date);
        List<Flight> flightsToDestination = findAllFlightsToDestination(destination, date);
        List<Ticket> result = new ArrayList<>();
        for (Flight flightFrom : flightsFromStartingLocation) {
            for (Flight flightTo : flightsToDestination) {
                if (checkPotentialConnectingFlight(flightFrom, flightTo)) {
                    Ticket ticket = addFlightsToTicket(flightFrom, flightTo);
                    result.add(ticket);
                }
            }
        }
        return result;
    }

    private boolean checkPotentialConnectingFlight(Flight flightFromStartingLocation, Flight checkedFlight) {
        return flightFromStartingLocation.getDestination().equals(checkedFlight.getStartingLocation()) && doesOneFlightArriveBeforeAnotherDepart(flightFromStartingLocation, checkedFlight);
    }

    private Ticket addFlightsToTicket(Flight flightFromStartingLocation, Flight potentialConnectingFlight) {
        Ticket connectedFlight = new Ticket();
        connectedFlight.getFlightsOnTicket().add(flightFromStartingLocation);
        connectedFlight.getFlightsOnTicket().add(potentialConnectingFlight);
        return connectedFlight;
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

    private void flightValidation(String startingLocation, String destination, LocalDateTime dateOfFlight)  {
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

}
