package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Journey;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightService;
import lombok.RequiredArgsConstructor;
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
    public List<Journey> getConnectedFlights(@RequestParam(name = "start") String startingLocalization, @RequestParam(name = "end") String destination, @RequestParam(name = "time", required = false) String dateOfFlight) {
        return flightService.getFlightsWithConnectingFlight(startingLocalization, destination, dateOfFlight);
    }

    @GetMapping("/allFlights")
    public List<Flight> getAllFlights() {
        return flightService.findAll();
    }

    @GetMapping("/getAllStartingLocations")
    public List<String> getStartingLocationsForAutocomplete(@RequestParam String input) {
        return flightService.findStartingLocationForAutocomplete(input);
    }

    @GetMapping("/getAllDestinations")
    public List<String> getDestinationForAutocomplete(@RequestParam String input) {
        return flightService.findDestinationForAutocomplete(input);
    }
}
