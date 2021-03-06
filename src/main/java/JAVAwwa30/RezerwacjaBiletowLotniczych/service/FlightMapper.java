package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.FlightDTO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class FlightMapper {

    public List<Flight> convertDataToListOfFlights(FlightDTO flightDTO) {
        List<FlightDTO.Data> data = flightDTO.getData();
        List<Flight> result = new ArrayList<>();
        for (FlightDTO.Data flight : data) {
            result.add(convertDataToFlight(flight));
        }
        return result;
    }

    public Flight convertDataToFlight(FlightDTO.Data data) {
        String departureAirport = data.getDeparture().getAirport();
        String arrivalAirport = data.getArrival().getAirport();
        LocalDateTime departureTime = LocalDateTime.parse(data.getDeparture().getScheduled(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime arrivalTime = LocalDateTime.parse(data.getArrival().getScheduled(), DateTimeFormatter.ISO_DATE_TIME);
        String duration = calculateDuration(arrivalTime, departureTime);
        return new Flight(departureAirport, arrivalAirport, duration, departureTime, arrivalTime);
    }

    private String calculateDuration(LocalDateTime arrivalTime, LocalDateTime departureTime) {
        return Long.toString(ChronoUnit.MINUTES.between(departureTime, arrivalTime));
    }
}
