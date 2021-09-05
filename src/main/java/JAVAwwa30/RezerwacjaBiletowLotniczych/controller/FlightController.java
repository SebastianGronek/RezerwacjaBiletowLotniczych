package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/findFlight/{startingLocalization}/{destination}")
    public List<Flight> getEveryFlightFromOneDestinationToAnother(@PathVariable String startingLocalization, @PathVariable String destination) {
        return flightService.getFlightsFromOneDestinationToAnother(startingLocalization, destination);
    }

    @GetMapping("/findFlight/{startingLocalization}/{destination}/{dateOfFlight}")
    public List<Flight> getFlightsFromOneDestinationToAnotherAfterDate(@PathVariable String startingLocalization, @PathVariable String destination,@PathVariable String dateOfFlight) {
        LocalDateTime date = LocalDateTime.parse(dateOfFlight);
        return flightService.getFlightsFromOneDestinationToAnotherAfterDate(startingLocalization, destination, date);
    }
}
