package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/findFlight")
    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(@RequestParam(name = "start") String startingLocalization, @RequestParam(name = "end") String destination, @RequestParam(name = "time", required = false) String dateOfFlight) {
        try {
            return flightService.getFlightsFromOneDestinationToAnotherAfterDate(startingLocalization, destination, dateOfFlight);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid input", e);
        }
    }
    @GetMapping("/allFlights")
    public List<Flight> getAllFlights() {
        return flightService.findAll();
    }
}
