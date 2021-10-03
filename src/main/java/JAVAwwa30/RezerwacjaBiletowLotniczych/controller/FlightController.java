package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidDateOfFlightException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLocationException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Ticket;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/findFlight")
    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(@RequestParam(name = "start") String startingLocalization, @RequestParam(name = "end") String destination, @RequestParam(name = "time", required = false) String dateOfFlight) {
        return flightService.getFlightsFromOneDestinationToAnotherAfterDate(startingLocalization, destination, dateOfFlight);
    }

    @GetMapping("/findConnectedFlight")
    public List<Ticket> getConnectedFlights(@RequestParam(name = "start") String startingLocalization, @RequestParam(name = "end") String destination, @RequestParam(name = "time", required = false) String dateOfFlight) {
        return flightService.getFlightsWithConnectingFlight(startingLocalization, destination, dateOfFlight);
    }

    @GetMapping("/allFlights")
    public List<Flight> getAllFlights() {
        return flightService.findAll();
    }
}
